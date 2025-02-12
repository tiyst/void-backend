package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotAccountDto;
import com.riotgames.model.RiotSummonerDTO;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.api.Server;
import st.tiy.budgetopgg.model.domain.mastery.ChampionMastery;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.dto.DtoSummoner;
import st.tiy.budgetopgg.model.dto.mapper.DtoSummonerMapper;
import st.tiy.budgetopgg.model.mapper.RiotAccountMapper;
import st.tiy.budgetopgg.model.mapper.RiotSummonerMapper;
import st.tiy.budgetopgg.repository.SummonerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SummonerService {

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

	public Optional<DtoSummoner> getSummoner(Server server, String gameName, String tagLine) {
		log.info("Get summoner by gameName: {}, tagLine: {}", gameName, tagLine);
		Optional<Summoner> summonerOptional = repository.findSummonerByGameNameIgnoreCaseAndTagLineIgnoreCase(gameName, tagLine);
		if (summonerOptional.isEmpty()) {
			return Optional.empty();
		}
		Summoner summoner = summonerOptional.get();
		List<Match> matches = this.matchService.getMatchesBySummoner(apiClient.serverToRegion(server), summoner);

		List<Rank> ranks = this.rankService.getRanksBySummonerId(server, summoner.getSummonerId());
		List<ChampionMastery> masteries = this.masteryService.getMasteryByPuuid(server, summoner.getPuuid());

		log.info("Get summoner by gameName: {}, tagLine: {} finished.", gameName, tagLine);
		return Optional.of(dtoSummonerMapper.toDtoSummoner(summoner, matches, ranks, masteries));
	}

	public DtoSummoner updateSummoner(Server server, String gameName, String tagLine) {
		log.info("Update summoner by gameName: {}, tagLine: {}", gameName, tagLine);
		RiotAccountDto response = apiClient.getAccount(server, gameName, tagLine);
		Summoner summoner = riotAccountDtoMapper.mapAccountDtoToSummoner(response);

		RiotSummonerDTO summonerResponse = apiClient.getSummoner(server, summoner.getPuuid());
		riotSummonerDtoMapper.mapSummonerDtoToSummoner(summonerResponse, summoner);

		summoner.setRank(pullRanks(server, summoner));
		summoner.setMasteries(pullMasteries(server, summoner));

		List<Match> matches = this.matchService.updateMatchesByPuuid(apiClient.serverToRegion(server), summoner.getPuuid());

		repository.save(summoner);
		log.info("Update summoner by gameName: {}, tagLine: {} finished.", gameName, tagLine);

		return dtoSummonerMapper.toDtoSummoner(summoner, matches);
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

}
