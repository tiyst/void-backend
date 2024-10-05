package st.tiy.budgetopgg.service;

import com.riotgames.model.MatchDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.domain.Match;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.repository.MatchRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MatchService {

	private static final String FETCH_MATCH_IDS_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids";
	private static final String FETCH_MATCH_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/%s";

	private final String API_KEY;
	private final RestTemplate restTemplate;
	private final MatchRepository matchRepository;

	public MatchService(@Value("${api.key}") String apiKey,
						RestTemplate restTemplate, MatchRepository matchRepository) {
		this.restTemplate = restTemplate;
		this.API_KEY = apiKey;
		this.matchRepository = matchRepository;
	}

	public List<Match> getMatchesBySummoner(Summoner summoner) {
		// missing transorm to DTO objects (feel free to return match, we'll dto-ify app later)
		return getMatchesByPuuid(summoner.getPuuid());
	}

	public List<MatchDto> getMatchesByPuuid(String puuid) {
		List<Match> cachedMatches = matchRepository.findAllByPlayersPuuidsContaining(puuid);

		if (!cachedMatches.isEmpty()) {
			return cachedMatches;
		}

		return pullNewMatchesByPuuid(puuid);
	}

	private List<MatchDto> pullNewMatchesByPuuid(String puuid) {
		String url = String.format(FETCH_MATCH_IDS_URL, puuid);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Riot-Token", API_KEY);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String[]> response = this.restTemplate.exchange(
				url, HttpMethod.GET, entity, String[].class);

		String[] matchIds = response.getBody();

		if (matchIds == null || matchIds.length == 0) {
			return Collections.emptyList();
		}

		List<MatchDto> updatedMatches = Arrays.stream(matchIds)
				.map(this::pullMatchByMatchId)
				.toList();

		// Can't save DTO objects / can't save objects without @Entity and @Id / it's a foreign dto object / we'll do later
		// unnecessary to touch / design domain objects
//		matchRepository.saveAll(updatedMatches);

		return updatedMatches;
	}

	private MatchDto pullMatchByMatchId(String matchId) {
		String url = String.format(FETCH_MATCH_URL, matchId);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Riot-Token", API_KEY);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<MatchDto> response = this.restTemplate.exchange(
				url, HttpMethod.GET, entity, MatchDto.class);

		return response.getBody();
	}

	public List<MatchDto> updateMatches(String puuid) { // unnecessary method
		return pullNewMatchesByPuuid(puuid);
	}
}