package st.tiy.budgetopgg.service;

import com.riotgames.model.AccountDto;
import com.riotgames.model.MatchDto;
import com.riotgames.model.SummonerDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.domain.Match;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.mapper.AccountDtoSummonerMapper;
import st.tiy.budgetopgg.model.mapper.SummonerDtoSummonerMapper;
import st.tiy.budgetopgg.repository.SummonerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SummonerService {

	public static final String ACCOUNT_BASE_URL = "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
	public static final String SUMMONER_BASE_URL = "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/";

	private final String API_KEY;
	private final MatchService matchService;
	private final RankService rankService;
	private final SummonerRepository repository;
	private final AccountDtoSummonerMapper accountDtoMapper;
	private final SummonerDtoSummonerMapper summonerDtoMapper;

	private RestTemplate restTemplate;

	public SummonerService(@Value("${api.key}") String apiKey,
	                       MatchService matchService,
	                       RankService rankService,
	                       SummonerRepository repository,
	                       AccountDtoSummonerMapper accountDtoMapper,
	                       SummonerDtoSummonerMapper summonerDtoMapper,
	                       RestTemplate restTemplate) {
		API_KEY = apiKey;
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
		String url = ACCOUNT_BASE_URL + gameName + "/" + tagLine;

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Riot-Token", API_KEY);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<AccountDto> response = restTemplate.exchange(
				url, HttpMethod.GET, entity, AccountDto.class);

		Summoner summoner = accountDtoMapper.mapAccountDtoToSummoner(response.getBody());

		url = SUMMONER_BASE_URL + summoner.getPuuid();

		ResponseEntity<SummonerDTO> summonerResponse = restTemplate.exchange(
				url, HttpMethod.GET, entity, SummonerDTO.class);

		summonerDtoMapper.mapSummonerDtoToSummoner(summonerResponse.getBody(), summoner);

		this.matchService.getMatchesBySummoner(summoner);
		List<Rank> rank = this.rankService.pullRankDataFromRiotApi(summoner.getSummonerId());
		summoner.setRank(rank);

		return repository.save(summoner);
	}

	public Summoner updateSummonerData(String gameName, String tagLine) {
		Summoner updatedSummoner = pullSummonerData(gameName, tagLine);

		updateRankAndMatch(updatedSummoner);

		return updatedSummoner;
	}

	private void updateRankAndMatch(Summoner summoner) {
		List<Rank> updatedRanks = rankService.updateRanks(summoner.getSummonerId());
		summoner.setRank(updatedRanks);

		List<MatchDto> updatedMatches = matchService.updateMatches(summoner.getPuuid());
		summoner.setMatches(updatedMatches);

		repository.save(summoner);
	}
}
