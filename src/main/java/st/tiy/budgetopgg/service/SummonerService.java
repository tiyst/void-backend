package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotAccountDto;
import com.riotgames.model.RiotSummonerDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.api.Server;
import st.tiy.budgetopgg.model.domain.mastery.ChampionMastery;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.mapper.AccountDtoSummonerMapper;
import st.tiy.budgetopgg.model.mapper.SummonerDtoSummonerMapper;
import st.tiy.budgetopgg.repository.SummonerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SummonerService {

	private final MatchService matchService;
	private final RankService rankService;
	private final ChampionMasteryService masteryService;
	private final SummonerRepository repository;
	private final AccountDtoSummonerMapper accountDtoMapper;
	private final SummonerDtoSummonerMapper summonerDtoMapper;
	private final RiotApiClient apiClient;

	private final RestTemplate restTemplate;

	public SummonerService(MatchService matchService, RankService rankService,
	                       ChampionMasteryService masteryService,
	                       SummonerRepository repository,
	                       AccountDtoSummonerMapper accountDtoMapper,
	                       SummonerDtoSummonerMapper summonerDtoMapper,
	                       RiotApiClient apiClient,
	                       RestTemplate restTemplate) {
		this.matchService = matchService;
		this.rankService = rankService;
		this.masteryService = masteryService;
		this.repository = repository;
		this.accountDtoMapper = accountDtoMapper;
		this.summonerDtoMapper = summonerDtoMapper;
		this.restTemplate = restTemplate;
		this.apiClient = apiClient;
	}

	public Optional<Summoner> getSummoner(Server server, String gameName, String tagLine) {
		return repository.findByGameNameAndTagLine(gameName, tagLine);
	}

	public Summoner updateSummoner(Server server, String gameName, String tagLine) {
		RiotAccountDto response = restTemplate.getForObject(apiClient.formatGetAccountUrl(server, gameName, tagLine), RiotAccountDto.class);
		Summoner summoner = accountDtoMapper.mapAccountDtoToSummoner(response);

		RiotSummonerDTO summonerResponse = restTemplate.getForObject(apiClient.formatGetSummonertUrl(server, summoner.getPuuid()), RiotSummonerDTO.class);
		summonerDtoMapper.mapSummonerDtoToSummoner(summonerResponse, summoner);

		this.matchService.getMatchesBySummoner(apiClient.serverToRegion(server), summoner);

		List<ChampionMastery> masteries = this.masteryService.getMasteryByPuuid(server, summoner.getPuuid());
		summoner.setMasteries(masteries);

		List<Rank> rank = this.rankService.getRanksBySummonerId(server, summoner.getSummonerId());
		summoner.setRank(rank);

		return repository.save(summoner);
	}

}
