package st.tiy.budgetopgg.service;

import com.riotgames.model.MatchDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MatchService {

	private static final String FETCH_MATCH_IDS_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids";
	private static final String FETCH_MATCH_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/%s";

	private final RestTemplate restTemplate;

	public MatchService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<MatchDto> getMatchesBySummoner(Summoner summoner) {
		return getMatchesByPuuid(summoner.getPuuid());
	}

	public List<MatchDto> getMatchesByPuuid(String puuid) {
		List<MatchDto> matchDtos = pullNewMatchesByPuuid(puuid);

		return matchDtos;
	}

	private List<MatchDto> pullNewMatchesByPuuid(String puuid) {
		String url = String.format(FETCH_MATCH_IDS_URL, puuid);
		String[] matchIds = this.restTemplate.getForObject(url, String[].class);

		if (matchIds == null || matchIds.length == 0) {
			return Collections.emptyList();
		}

		return Arrays.stream(matchIds)
				.map(this::pullMatchByMatchId)
				.toList();
	}

	private MatchDto pullMatchByMatchId(String matchId) {
		String url = String.format(FETCH_MATCH_URL, matchId);
		MatchDto match = this.restTemplate.getForObject(url, MatchDto.class);
		// TODO mapping

		return match;
	}

}
