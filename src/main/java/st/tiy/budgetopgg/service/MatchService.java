package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotMatchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.api.Region;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.mapper.MatchDtoMatchMapper;
import st.tiy.budgetopgg.repository.MatchRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MatchService {

	private final RestTemplate restTemplate;
	private final MatchRepository matchRepository;
	private final MatchDtoMatchMapper mapper;
	private final RiotApiClient apiClient;

	public MatchService(RestTemplate restTemplate,
	                    MatchRepository matchRepository,
	                    MatchDtoMatchMapper mapper,
	                    RiotApiClient apiClient) {
		this.restTemplate = restTemplate;
		this.matchRepository = matchRepository;
		this.mapper = mapper;
		this.apiClient = apiClient;
	}

	public List<Match> getMatchesBySummoner(Region region, Summoner summoner) {
		return getMatchesByPuuid(region, summoner.getPuuid());
	}

	public List<Match> getMatchesByPuuid(Region region, String puuid) {
		return pullNewMatchesByPuuid(region, puuid);
	}

	private List<Match> pullNewMatchesByPuuid(Region region, String puuid) {
		String[] matchIds = this.restTemplate.getForObject(apiClient.formatGetMatchIdsUrl(region, puuid), String[].class);

		if (matchIds == null || matchIds.length == 0) {
			return Collections.emptyList();
		}

		List<Match> matches = Arrays.stream(matchIds)
		                            .map(id -> pullMatchByMatchId(region, id))
		                            .filter(Optional::isPresent)
		                            .map(Optional::get)
		                            .toList();

		matchRepository.saveAll(matches);

		return matches;
	}

	private Optional<Match> pullMatchByMatchId(Region region, String matchId) {
		RiotMatchDto match = this.restTemplate.getForObject(apiClient.formatGetMatchUrl(region, matchId), RiotMatchDto.class);

		if (match == null) {
			log.error("Retrieving match failed for {}", matchId);
			return Optional.empty();
		}

		return Optional.of(mapper.mapToMatch(match));
	}

}
