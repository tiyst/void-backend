package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotMatchDto;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.mapper.MatchDtoMatchMapper;
import st.tiy.budgetopgg.repository.MatchRepository;

@Service
public class MatchService {

	private static final String FETCH_MATCH_IDS_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids";
	private static final String FETCH_MATCH_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/%s";

	private final RestTemplate restTemplate;
	private final MatchRepository matchRepository;
	private final MatchDtoMatchMapper mapper;

	public MatchService(RestTemplate restTemplate, MatchRepository matchRepository, MatchDtoMatchMapper mapper) {
		this.restTemplate = restTemplate;
		this.matchRepository = matchRepository;
		this.mapper = mapper;
	}

	public List<Match> getMatchesBySummoner(Summoner summoner) {
		return getMatchesByPuuid(summoner.getPuuid());
	}

	public List<Match> getMatchesByPuuid(String puuid) {
		List<Match> matchDtos = pullNewMatchesByPuuid(puuid);

		return matchDtos;
	}

	private List<Match> pullNewMatchesByPuuid(String puuid) {
		String url = String.format(FETCH_MATCH_IDS_URL, puuid);
		String[] matchIds = this.restTemplate.getForObject(url, String[].class);

		if (matchIds == null || matchIds.length == 0) {
			return Collections.emptyList();
		}

		List<Match> matches = Arrays.stream(matchIds)
			.map(this::pullMatchByMatchId)
			.toList();

		matchRepository.saveAll(matches);

		return matches;
	}

	private Match pullMatchByMatchId(String matchId) {
		String url = String.format(FETCH_MATCH_URL, matchId);
		RiotMatchDto match = this.restTemplate.getForObject(url, RiotMatchDto.class);

		return mapper.mapToMatch(match);
	}

}
