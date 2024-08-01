package st.tiy.budgetopgg.service;

import com.riotgames.model.LeagueEntryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.model.mapper.RankMapper;
import st.tiy.budgetopgg.repository.RankRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RankService {

	public static final String RANK_BASE_URL = "https://eun1.api.riotgames.com/lol/league/v4/entries/by-summoner/%s?api_key=%s";

	private final String API_KEY;

	private final RestTemplate restTemplate;
	private final RankMapper rankMapper;

	private final RankRepository rankRepository;

	public RankService(@Value("${api.key}") String apiKey,
	                   RestTemplate restTemplate,
	                   RankMapper rankMapper,
	                   RankRepository rankRepository) {
		this.API_KEY = apiKey;
		this.restTemplate = restTemplate;
		this.rankMapper = rankMapper;
		this.rankRepository = rankRepository;
	}

	public List<Rank> getRanksBySummonerId(String summonerId) {
		String url = String.format(RANK_BASE_URL, summonerId, API_KEY);
		LeagueEntryDTO[] response = restTemplate.getForObject(url, LeagueEntryDTO[].class);

		if (response == null) {
			return Collections.emptyList();
		}

		return Arrays.stream(response)
				.map(this.rankMapper::mapToRank)
				.toList();
	}

}
