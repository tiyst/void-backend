package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotAccountDto;
import com.riotgames.model.RiotSummonerDTO;
import org.springframework.stereotype.Service;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.api.Server;
import st.tiy.budgetopgg.model.domain.mastery.ChampionMastery;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.dto.DtoSummoner;
import st.tiy.budgetopgg.model.mapper.RiotAccountMapper;
import st.tiy.budgetopgg.model.mapper.RiotSummonerMapper;
import st.tiy.budgetopgg.repository.SummonerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SummonerService {

	private final MatchService matchService;
	private final RankService rankService;
	private final ChampionMasteryService masteryService;
	private final SummonerRepository repository;
	private final RiotAccountMapper riotAccountDtoMapper;
	private final RiotSummonerMapper riotSummonerDtoMapper;
	private final RiotApiClient apiClient;

	public SummonerService(MatchService matchService,
	                       RankService rankService,
	                       ChampionMasteryService masteryService,
	                       SummonerRepository repository,
	                       RiotAccountMapper riotAccountDtoMapper,
	                       RiotSummonerMapper riotSummonerDtoMapper,
	                       RiotApiClient apiClient) {
		this.matchService = matchService;
		this.rankService = rankService;
		this.masteryService = masteryService;
		this.repository = repository;
		this.riotAccountDtoMapper = riotAccountDtoMapper;
		this.riotSummonerDtoMapper = riotSummonerDtoMapper;
		this.apiClient = apiClient;
	}

	public Optional<Summoner> getSummoner(Server server, String gameName, String tagLine) {
		return repository.findByGameNameAndTagLine(gameName, tagLine);
	}

	public Summoner updateSummoner(Server server, String gameName, String tagLine) {
		RiotAccountDto response = apiClient.getAccount(server, gameName, tagLine);
		Summoner summoner = riotAccountDtoMapper.mapAccountDtoToSummoner(response);

		RiotSummonerDTO summonerResponse = apiClient.getSummoner(server, summoner.getPuuid());
		riotSummonerDtoMapper.mapSummonerDtoToSummoner(summonerResponse, summoner);

		this.matchService.getMatchesBySummoner(apiClient.serverToRegion(server), summoner);

		List<ChampionMastery> masteries = this.masteryService.getMasteryByPuuid(server, summoner.getPuuid());
		summoner.setMasteries(masteries);

		List<Rank> rank = this.rankService.getRanksBySummonerId(server, summoner.getSummonerId());
		summoner.setRank(rank);

		return repository.save(summoner);
	}

}
