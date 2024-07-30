package st.tiy.budgetopgg.service;

import com.riotgames.model.LeagueEntryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RankService {
	public static final String RANK_BASE_URL = "https://eun1.api.riotgames.com/lol/league/v4/entries/by-summoner/%s?api_key=%s";

	// TODO
	// rank repository
	// mapper into domain Rank entity

	private final String API_KEY;

	private final RestTemplate restTemplate;

	public RankService(@Value("${api.key}") String apiKey,
	                   RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.API_KEY = apiKey;
	}

	public void getRanksSummonerId(String summonerId) {
		String url = String.format(RANK_BASE_URL, summonerId, API_KEY);
		LeagueEntryDTO[] response = restTemplate.getForObject(url, LeagueEntryDTO[].class);

		// TODO Finish

	}




}
