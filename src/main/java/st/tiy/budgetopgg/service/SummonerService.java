package st.tiy.budgetopgg.service;

import com.riotgames.model.AccountDto;
import com.riotgames.model.SummonerDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.mapper.AccountDtoSummonerMapper;
import st.tiy.budgetopgg.model.mapper.SummonerDtoSummonerMapper;
import st.tiy.budgetopgg.repository.SummonerRepository;

import java.util.Optional;

@Service
public class SummonerService {

	public static final String ACCOUNT_BASE_URL = "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
	public static final String SUMMONER_BASE_URL = "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/";

	private final String API_KEY;

	private MatchService matchService;
	private SummonerRepository repository;
	private AccountDtoSummonerMapper accountDtoMapper;
	private SummonerDtoSummonerMapper summonerDtoMapper;

	private RestTemplate restTemplate;

	public SummonerService(@Value("${api.key}") String apiKey,
	                       MatchService matchService,
	                       SummonerRepository repository,
	                       AccountDtoSummonerMapper accountDtoMapper,
	                       SummonerDtoSummonerMapper summonerDtoMapper,
	                       RestTemplate restTemplate) {
		API_KEY = apiKey;
		this.matchService = matchService;
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
		String query = ACCOUNT_BASE_URL + gameName + "/" + tagLine + "?api_key=" + API_KEY;
		AccountDto response = restTemplate.getForObject(query, AccountDto.class);

		Summoner summoner = accountDtoMapper.mapAccountDtoToSummoner(response);

		query = SUMMONER_BASE_URL + summoner.getPuuid() + "?api_key=" + API_KEY;
		SummonerDTO summonerResponse = restTemplate.getForObject(query, SummonerDTO.class);
		summonerDtoMapper.mapSummonerDtoToSummoner(summonerResponse, summoner);

		this.matchService.getMatchesBySummoner(summoner);
		// TODO CALL TO MATCH SERVICE

		// TODO save to repo
		// TODO cachedSummoner = newly found

		return summoner;
	}

}
