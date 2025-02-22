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
import st.tiy.voidapp.model.domain.summoner.Rank;
import st.tiy.voidapp.model.domain.summoner.Summoner;
import st.tiy.voidapp.model.dto.DtoSummoner;
import st.tiy.voidapp.model.dto.mapper.DtoSummonerMapper;
import st.tiy.voidapp.model.mapper.RiotAccountMapper;
import st.tiy.voidapp.model.mapper.RiotSummonerMapper;
import st.tiy.voidapp.repository.SummonerRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SummonerService {
	@Value("${voidapp.update.enabled:true}") private boolean updateThrottleEnabled;
	@Value("${voidapp.update.delay:2}") private int updateThrottleMinutes;

	private final MatchService matchService;
	private final RankService rankService;
	private final ChampionMasteryService masteryService;
	private final SummonerRepository repository;
	private final RiotAccountMapper riotAccountDtoMapper;
	private final RiotSummonerMapper riotSummonerDtoMapper;
	private final DtoSummonerMapper dtoSummonerMapper;
	private final RiotApiClient apiClient;

	public SummonerService(MatchService matchService,
	                       RankService rankService,
	                       ChampionMasteryService masteryService,
	                       SummonerRepository repository,
	                       RiotAccountMapper riotAccountDtoMapper,
	                       RiotSummonerMapper riotSummonerDtoMapper,
	                       DtoSummonerMapper dtoSummonerMapper,
	                       RiotApiClient apiClient) {
		this.matchService = matchService;
		this.rankService = rankService;
		this.masteryService = masteryService;
		this.repository = repository;
		this.riotAccountDtoMapper = riotAccountDtoMapper;
		this.riotSummonerDtoMapper = riotSummonerDtoMapper;
		this.dtoSummonerMapper = dtoSummonerMapper;
		this.apiClient = apiClient;
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

		Summoner summoner = pullSummoner(server, gameName, tagLine);
		summoner.setRank(pullRanks(server, summoner));
		summoner.setMasteries(pullMasteries(server, summoner));

		List<Match> matches = this.matchService.updateMatchesByPuuid(apiClient.serverToRegion(server), summoner.getPuuid());

		repository.save(summoner);
		log.info("Update summoner by gameName: {}, tagLine: {} finished.", gameName, tagLine);

		return dtoSummonerMapper.toDtoSummoner(summoner, matches);
	}

	private void checkUpdateThrottling(Summoner summoner) {
		if (updateThrottleEnabled) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime lastUpdate = LocalDateTime.ofInstant(Instant.ofEpochSecond(summoner.getLastUpdated()), ZoneId.systemDefault());

			if (now.isBefore(lastUpdate.plusMinutes(updateThrottleMinutes))) {
				LocalDateTime updateAvailable = lastUpdate.plusMinutes(updateThrottleMinutes);
				String errorMessage = "Summoner %s#%s updated too frequently. Try again in %s".formatted(
						summoner.getGameName(),
						summoner.getTagLine(),
						updateAvailable.toString());
				throw new SummonerUpdateTooFrequentException(errorMessage);
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
