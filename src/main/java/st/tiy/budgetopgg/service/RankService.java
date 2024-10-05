package st.tiy.budgetopgg.service;

import com.riotgames.model.LeagueEntryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

	public static final String RANK_BASE_URL = "https://eun1.api.riotgames.com/lol/league/v4/entries/by-summoner/%s";

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
		List<Rank> cachedRanks = rankRepository.findBySummonerId(summonerId);

		if (!cachedRanks.isEmpty()) {
			return cachedRanks;
		}

		return pullRankDataFromRiotApi(summonerId);
	}

	public List<Rank> updateRanks(String summonerId) {
		return pullRankDataFromRiotApi(summonerId); // Call RiotAPI and update repository
	}

	public List<Rank> pullRankDataFromRiotApi(String summonerId) {
		String url = String.format(RANK_BASE_URL, summonerId);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Riot-Token", API_KEY);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<LeagueEntryDTO[]> response = restTemplate.exchange(
				url, HttpMethod.GET, entity, LeagueEntryDTO[].class);

		if (response.getBody() == null) {
			return Collections.emptyList();
		}

		List<Rank> updatedRanks = Arrays.stream(response.getBody())
				.map(rankMapper::mapToRank)
				.toList();

		rankRepository.saveAll(updatedRanks);

		return updatedRanks;
	}

}
