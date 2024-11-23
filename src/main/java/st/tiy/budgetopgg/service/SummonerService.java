package st.tiy.budgetopgg.service;

import com.riotgames.model.AccountDto;
import com.riotgames.model.SummonerDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
	private final SummonerRepository repository;
	private final AccountDtoSummonerMapper accountDtoMapper;
	private final SummonerDtoSummonerMapper summonerDtoMapper;

	private final RestTemplate restTemplate;

	public SummonerService(MatchService matchService,
	                       RankService rankService,
	                       SummonerRepository repository,
	                       AccountDtoSummonerMapper accountDtoMapper,
	                       SummonerDtoSummonerMapper summonerDtoMapper,
	                       RestTemplate restTemplate) {
		this.matchService = matchService;
		this.rankService = rankService;
		this.repository = repository;
		this.accountDtoMapper = accountDtoMapper;
		this.summonerDtoMapper = summonerDtoMapper;
		this.restTemplate = restTemplate;
	}

	public Summoner getSummoner(String gameName, String tagLine) {
		Optional<Summoner> cachedSummoner = repository.findByGameNameAndTagLine(gameName, tagLine);

		if (cachedSummoner.isEmpty()) {
			return pullSummonerData(gameName, tagLine);
		}

		return cachedSummoner.orElse(null);
	}

	private Summoner pullSummonerData(String gameName, String tagLine) {
		String query = ACCOUNT_BASE_URL.formatted(gameName, tagLine);
		AccountDto response = restTemplate.getForObject(query, AccountDto.class);

		Summoner summoner = accountDtoMapper.mapAccountDtoToSummoner(response);

		query = SUMMONER_BASE_URL.formatted(summoner.getPuuid());
		SummonerDTO summonerResponse = restTemplate.getForObject(query, SummonerDTO.class);
		summonerDtoMapper.mapSummonerDtoToSummoner(summonerResponse, summoner);

		this.matchService.getMatchesBySummoner(summoner);

		List<Rank> rank = this.rankService.getRanksBySummonerId(summoner.getSummonerId());
		summoner.setRank(rank);

		return repository.save(summoner);
	}

}
