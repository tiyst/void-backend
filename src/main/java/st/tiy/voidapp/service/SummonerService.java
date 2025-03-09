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
import st.tiy.voidapp.repository.SummonerRepository;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class SummonerService {
	@Value("${voidapp.update.enabled:true}")
	private boolean updateThrottleEnabled;
	@Value("${voidapp.update.delay:2}")
	private int updateThrottleMinutes;

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
	                       TaskQueueService taskQueueService) {
		this.matchService = matchService;
		this.rankService = rankService;
		this.masteryService = masteryService;
		this.repository = repository;
		this.riotAccountDtoMapper = riotAccountDtoMapper;
		this.riotSummonerDtoMapper = riotSummonerDtoMapper;
		this.dtoSummonerMapper = dtoSummonerMapper;
		this.apiClient = apiClient;
		this.taskQueueService = taskQueueService;
	}

	public DtoSummoner getSummoner(Server server, String gameName, String tagLine) {
		log.info("Get summoner by gameName: {}, tagLine: {}", gameName, tagLine);
		Optional<Summoner> summonerOptional = repository.findSummonerByGameNameIgnoreCaseAndTagLineIgnoreCase(gameName, tagLine);

		if (summonerOptional.isEmpty()) {
			log.info("Get summoner by gameName: {}, tagLine: {} EMPTY.", gameName, tagLine);
			throw new SummonerNotFoundException("Summoner not found %s#%s".formatted(gameName, tagLine));
		}

		Summoner summoner = summonerOptional.get();
		List<Match> matches = this.matchService.getMatchesBySummoner(apiClient.serverToRegion(server), summoner);

		List<Rank> ranks = this.rankService.getRanksBySummonerId(server, summoner.getSummonerId());
		List<ChampionMastery> masteries = this.masteryService.getMasteryByPuuid(server, summoner.getPuuid());

		log.info("Get summoner by gameName: {}, tagLine: {} finished.", gameName, tagLine);
		return dtoSummonerMapper.toDtoSummoner(summoner, matches, ranks, masteries);
	}

	public DtoSummoner updateSummoner(Server server, String gameName, String tagLine) {
		log.info("Update summoner by gameName: {}, tagLine: {}", gameName, tagLine);

		Optional<Summoner> summonerOptional = repository.findSummonerByGameNameIgnoreCaseAndTagLineIgnoreCase(gameName, tagLine);
		summonerOptional.ifPresent(this::checkUpdateThrottling);

		Summoner summoner = fetchBasicSummonerInfo(server, gameName, tagLine);
		List<Match> matches = this.matchService.updateMatchesByPuuid(apiClient.serverToRegion(server), summoner.getPuuid());

		List<Participant> matchParticipants = filterUniqueSummonersFromMatches(matches);
		submitBasicFetchTasks(server, matchParticipants);

		repository.save(summoner);
		log.info("Update summoner by gameName: {}, tagLine: {} finished.", gameName, tagLine);

		return dtoSummonerMapper.toDtoSummoner(summoner, matches);
	}

	/**
	 * Used when match is pulled, and we want to pull basic summoner structure without pulling their matches.
	 * This reduces friction for new users if they don't have to update manually and see their basic stats and matches other users have updated.
	 */
	public void updateBasicSummoner(Server server, String gameName, String tagLine) {
		Summoner summoner = fetchBasicSummonerInfo(server, gameName, tagLine);

		repository.save(summoner);
	}

	private void submitBasicFetchTasks(Server server, List<Participant> participants) {
		participants.forEach(participant -> taskQueueService.submitTask(new BasicSummonerProcessTask(
				BasicSummonerProcessTaskParams.builder()
				                              .server(server)
				                              .gameName(participant.getRiotIdGameName())
				                              .tagLine(participant.getRiotIdTagline())
				                              .build()
		)));
	}

	private Summoner fetchBasicSummonerInfo(Server server, String gameName, String tagLine) {
		Summoner summoner = pullSummoner(server, gameName, tagLine);
		summoner.setRank(pullRanks(server, summoner));
		summoner.setMasteries(pullMasteries(server, summoner));

		return summoner;
	}

	private List<Participant> filterUniqueSummonersFromMatches(List<Match> matches) {
		Set<String> uniquePuuids = new HashSet<>();

		return matches.stream()
		              .flatMap(match -> match.getParticipantList().stream())
		              .filter(p -> uniquePuuids.add(p.getPuuid()))
		              .toList();
	}

	private void checkUpdateThrottling(Summoner summoner) {
		if (updateThrottleEnabled) {
			ZonedDateTime now = ZonedDateTime.now();
			ZonedDateTime lastUpdate = ZonedDateTime.ofInstant(Instant.ofEpochSecond(summoner.getLastUpdated()), ZoneId.systemDefault());

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
		List<ChampionMastery> masteries = this.masteryService.getMasteryByPuuid(server, summoner.getPuuid());
		masteries.forEach(m -> m.setSummoner(summoner));

		return masteries;
	}

	private List<Rank> pullRanks(Server server, Summoner summoner) {
		List<Rank> ranks = this.rankService.getRanksBySummonerId(server, summoner.getSummonerId());
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
