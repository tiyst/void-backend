package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotAccountDto;
import com.riotgames.model.RiotSummonerDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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

	public static final String ACCOUNT_BASE_URL = "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/%s";
	public static final String SUMMONER_BASE_URL = "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/%s";

	private final MatchService matchService;
	private final RankService rankService;
	private final ChampionMasteryService masteryService;
	private final SummonerRepository repository;
	private final AccountDtoSummonerMapper accountDtoMapper;
	private final SummonerDtoSummonerMapper summonerDtoMapper;

	private final RestTemplate restTemplate;

	public SummonerService(MatchService matchService, RankService rankService, ChampionMasteryService masteryService,
	                       SummonerRepository repository, AccountDtoSummonerMapper accountDtoMapper,
	                       SummonerDtoSummonerMapper summonerDtoMapper, RestTemplate restTemplate) {
		this.matchService = matchService;
		this.rankService = rankService;
		this.masteryService = masteryService;
		this.repository = repository;
		this.accountDtoMapper = accountDtoMapper;
		this.summonerDtoMapper = summonerDtoMapper;
		this.restTemplate = restTemplate;
	}

	public Optional<Summoner> getSummoner(String gameName, String tagLine) {
		return repository.findByGameNameAndTagLine(gameName, tagLine);
	}

	public Summoner updateSummoner(String gameName, String tagLine) {
		String query = ACCOUNT_BASE_URL.formatted(gameName, tagLine);
		RiotAccountDto response = restTemplate.getForObject(query, RiotAccountDto.class);

		Summoner summoner = accountDtoMapper.mapAccountDtoToSummoner(response);

		query = SUMMONER_BASE_URL.formatted(summoner.getPuuid());
		RiotSummonerDTO summonerResponse = restTemplate.getForObject(query, RiotSummonerDTO.class);
		summonerDtoMapper.mapSummonerDtoToSummoner(summonerResponse, summoner);

		this.matchService.getMatchesBySummoner(summoner);

		List<ChampionMastery> masteries = this.masteryService.getMasteryByPuuid(summoner.getPuuid());
		summoner.setMasteries(masteries);

		List<Rank> rank = this.rankService.getRanksBySummonerId(summoner.getSummonerId());
		summoner.setRank(rank);

		return repository.save(summoner);
	}

}
