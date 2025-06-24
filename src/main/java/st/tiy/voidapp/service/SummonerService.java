package st.tiy.voidapp.service;

import com.riotgames.model.RiotAccountDto;
import com.riotgames.model.RiotSummonerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.api.RiotApiClient;
import st.tiy.voidapp.api.Server;
import st.tiy.voidapp.exception.SummonerNotFoundException;
import st.tiy.voidapp.exception.SummonerUpdateTooFrequentException;
import st.tiy.voidapp.model.domain.mastery.ChampionMastery;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.domain.match.team.Participant;
import st.tiy.voidapp.model.domain.summoner.Rank;
import st.tiy.voidapp.model.domain.summoner.Summoner;
import st.tiy.voidapp.model.dto.DtoSummoner;
import st.tiy.voidapp.model.dto.mapper.DtoSummonerMapper;
import st.tiy.voidapp.model.mapper.RiotAccountMapper;
import st.tiy.voidapp.model.mapper.RiotSummonerMapper;
import st.tiy.voidapp.queue.TaskQueueService;
import st.tiy.voidapp.queue.task.summonerfetch.BasicSummonerProcessTask;
import st.tiy.voidapp.queue.task.summonerfetch.BasicSummonerProcessTaskParams;
import st.tiy.voidapp.queue.task.trophyroom.TrophyRoomTask;
import st.tiy.voidapp.queue.task.trophyroom.TrophyRoomTaskParameters;
import st.tiy.voidapp.repository.SummonerRepository;
import st.tiy.voidapp.trophy.TrophyRoomService;
import st.tiy.voidapp.trophy.trophies.Trophy;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static st.tiy.voidapp.utils.SummonerUtils.getCurrentTimestampForLastUpdated;
import static st.tiy.voidapp.utils.SummonerUtils.isSummonerEligibleForUpdate;

@Service
@Slf4j
public class SummonerService {
	private final TrophyRoomService trophyRoomService;
	@Value("${voidapp.update.enabled:true}")
	private boolean updateThrottleEnabled;
	@Value("${voidapp.update.delay:2}")
	private int updateThrottleMinutes;
	@Value("${voidapp.update.min-age-threshold:21}")
	private int minAgeThreshold; // Minimum days after which summoner is eligible for basic update through match participation

	private final MatchService matchService;
	private final RankService rankService;
	private final ChampionMasteryService masteryService;
	private final SummonerRepository repository;
	private final RiotAccountMapper riotAccountDtoMapper;
	private final RiotSummonerMapper riotSummonerDtoMapper;
	private final DtoSummonerMapper dtoSummonerMapper;
	private final RiotApiClient apiClient;
	private final TaskQueueService taskQueueService;

	public SummonerService(MatchService matchService,
	                       RankService rankService,
	                       ChampionMasteryService masteryService,
	                       SummonerRepository repository,
	                       RiotAccountMapper riotAccountDtoMapper,
	                       RiotSummonerMapper riotSummonerDtoMapper,
	                       DtoSummonerMapper dtoSummonerMapper,
	                       RiotApiClient apiClient,
	                       TaskQueueService taskQueueService, TrophyRoomService trophyRoomService) {
		this.matchService = matchService;
		this.rankService = rankService;
		this.masteryService = masteryService;
		this.repository = repository;
		this.riotAccountDtoMapper = riotAccountDtoMapper;
		this.riotSummonerDtoMapper = riotSummonerDtoMapper;
		this.dtoSummonerMapper = dtoSummonerMapper;
		this.apiClient = apiClient;
		this.taskQueueService = taskQueueService;
		this.trophyRoomService = trophyRoomService;
	}

	public DtoSummoner getSummoner(Server server, String gameName, String tagLine) {
		log.info("Get summoner by gameName: {}, tagLine: {}", gameName, tagLine);
		Optional<Summoner> summonerOptional = repository.findSummonerByGameNameIgnoreCaseAndTagLineIgnoreCase(gameName, tagLine);

		if (summonerOptional.isEmpty()) {
			log.info("Get summoner by gameName: {}, tagLine: {} EMPTY.", gameName, tagLine);
			throw new SummonerNotFoundException("Summoner not found %s#%s".formatted(gameName, tagLine));
		}

		Summoner summoner = summonerOptional.get();
		List<Match> matches = this.matchService.getInitialMatchesByPuuid(summoner.getPuuid());

		List<Rank> ranks = this.rankService.getRanksBySummonerId(server, summoner.getSummonerId());
		List<ChampionMastery> masteries = this.masteryService.getMasteryByPuuid(server, summoner.getPuuid());
		List<Trophy> trophies = this.trophyRoomService.getTrophies(summoner);

		log.info("Get summoner by gameName: {}, tagLine: {} start mapping.", gameName, tagLine);
		DtoSummoner dtoSummoner = dtoSummonerMapper.toDtoSummoner(summoner, matches, ranks, masteries, trophies);
		log.info("Get summoner by gameName: {}, tagLine: {} finished.", gameName, tagLine);
		return dtoSummoner;
	}

	public DtoSummoner updateSummoner(Server server, String gameName, String tagLine) {
		log.info("Update summoner by gameName: {}, tagLine: {}", gameName, tagLine);

		Optional<Summoner> summonerOptional = repository.findSummonerByGameNameIgnoreCaseAndTagLineIgnoreCase(gameName, tagLine);
		summonerOptional.ifPresent(this::checkUpdateThrottling);

		Summoner summoner = fetchBasicSummonerInfo(server, gameName, tagLine);
		List<Match> matches = this.matchService.updateMatchesByPuuid(apiClient.serverToRegion(server), summoner.getPuuid());

		repository.save(summoner);

		List<Trophy> trophies = trophyRoomService.processMatches(matches, summoner);

		Map<Participant, List<Match>> matchParticipants = filterUniqueSummonersFromMatches(matches);
		submitBasicFetchTasks(server, matchParticipants);

		log.info("Mapping started by gameName: {}, tagLine: {}", gameName, tagLine);
		DtoSummoner dtoSummoner = dtoSummonerMapper.toDtoSummoner(summoner, matches, trophies);
		log.info("Mapping finished by gameName: {}, tagLine: {}", gameName, tagLine);

		log.info("Update summoner by gameName: {}, tagLine: {} finished.", gameName, tagLine);
		return dtoSummoner;
	}

	/**
	 * Used when match is pulled, and we want to pull basic summoner structure without pulling their matches.
	 * This reduces friction for new users if they don't have to update manually and see their basic stats and matches other users have updated.
	 */
	public void updateBasicSummoner(Server server, String gameName, String tagLine, List<Match> matches) {
		Optional<Summoner> optSummoner = repository.findSummonerByGameNameIgnoreCaseAndTagLineIgnoreCase(gameName, tagLine);
		if (optSummoner.isPresent() && !isSummonerEligibleForUpdate(optSummoner.get(), minAgeThreshold)) {
			return;
		}

		Summoner summoner = fetchBasicSummonerInfo(server, gameName, tagLine);
		summoner.setLastUpdated(getCurrentTimestampForLastUpdated());

		repository.save(summoner);
		trophyRoomService.processMatches(matches, summoner);
	}

	private void submitBasicFetchTasks(Server server, Map<Participant, List<Match>> participants) {
		participants.forEach((participant, matches) ->
		{
			BasicSummonerProcessTask task = new BasicSummonerProcessTask(
					BasicSummonerProcessTaskParams.builder()
					                              .server(server)
					                              .gameName(participant.getRiotIdGameName())
					                              .tagLine(participant.getRiotIdTagline())
					                              .matches(matches)
					                              .build()
			);
			TrophyRoomTask trophyTask = new TrophyRoomTask(
					TrophyRoomTaskParameters.builder()
							.matchIds(matches.stream().map(Match::getMatchId).toList())
							.puuid(participant.getPuuid())
							.build()
			);
			taskQueueService.submitTask(task);
			taskQueueService.submitTask(trophyTask);
		});

	}

	private Summoner fetchBasicSummonerInfo(Server server, String gameName, String tagLine) {
		Summoner summoner = pullSummoner(server, gameName, tagLine);
		summoner.setRank(pullRanks(server, summoner));
		summoner.setMasteries(pullMasteries(server, summoner));

		return summoner;
	}

	private Map<Participant, List<Match>> filterUniqueSummonersFromMatches(List<Match> matches) {
		return matches.stream()
		              .flatMap(match -> match.getParticipantList()
		                                     .stream()
		                                     .map(p -> Map.entry(p, match)))
		              .collect(Collectors.groupingBy(
				              Map.Entry::getKey,
				              Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
	}

	private void checkUpdateThrottling(Summoner summoner) {
		if (updateThrottleEnabled) {
			ZonedDateTime now = ZonedDateTime.now();
			ZonedDateTime lastUpdate = ZonedDateTime.ofInstant(Instant.ofEpochSecond(summoner.getLastUpdated()), ZoneId.of("UTC"));

			if (now.isBefore(lastUpdate.plusMinutes(updateThrottleMinutes))) {
				ZonedDateTime updateAvailable = lastUpdate.plusMinutes(updateThrottleMinutes);
				String errorMessage = "Summoner %s#%s updated too frequently. Try again in %s".formatted(
						summoner.getGameName(),
						summoner.getTagLine(),
						updateAvailable.toString());
				throw new SummonerUpdateTooFrequentException(errorMessage, updateAvailable);
			}
		}
	}

	private List<ChampionMastery> pullMasteries(Server server, Summoner summoner) {
		List<ChampionMastery> masteries = this.masteryService.pullMasteryByPuuid(server, summoner.getPuuid());
		masteries.forEach(m -> m.setSummoner(summoner));

		return masteries;
	}

	private List<Rank> pullRanks(Server server, Summoner summoner) {
		List<Rank> ranks = this.rankService.pullRanksBySummonedPuuid(server, summoner.getPuuid());
		ranks.forEach(rank -> rank.setSummoner(summoner));

		return ranks;
	}

	private Summoner pullSummoner(Server server, String gameName, String tagLine) {
		RiotAccountDto response = apiClient.getAccount(server, gameName, tagLine);
		Summoner summoner = riotAccountDtoMapper.mapAccountDtoToSummoner(response);
		RiotSummonerDTO summonerResponse = apiClient.getSummoner(server, summoner.getPuuid());

		return riotSummonerDtoMapper.mapSummonerDtoToSummoner(summonerResponse, summoner);
	}

}
