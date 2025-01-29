package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotMatchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import st.tiy.budgetopgg.api.Region;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.mapper.RiotMatchDtoMatchMapper;
import st.tiy.budgetopgg.repository.MatchRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MatchService {

	private final MatchRepository matchRepository;
	private final RiotMatchDtoMatchMapper riotMapper;
	private final RiotApiClient apiClient;

	public MatchService(MatchRepository matchRepository,
	                    RiotMatchDtoMatchMapper riotMapper,
	                    RiotApiClient apiClient) {
		this.matchRepository = matchRepository;
		this.riotMapper = riotMapper;
		this.apiClient = apiClient;
	}

	public List<Match> getMatchesBySummoner(Region region, Summoner summoner) {
		return getMatchesByPuuid(region, summoner.getPuuid());
	}

	public List<Match> getMatchesByPuuid(Region region, String puuid) {
		return matchRepository.findAllByParticipantIdsContaining(puuid);
	}

	public List<Match> updateMatchesByPuuid(Region region, String puuid) {
		return pullNewMatchesByPuuid(region, puuid);
	}

	// TODO When querying for new matches, update only since the last match timestamp
	private List<Match> pullNewMatchesByPuuid(Region region, String puuid) {
		String[] matchIds = apiClient.getMatchIds(region, puuid);

		List<Match> matches = Arrays.stream(matchIds)
		                            .parallel()
		                            .map(id -> pullMatchByMatchId(region, id))
		                            .filter(Optional::isPresent)
		                            .map(Optional::get)
		                            .toList();

		matchRepository.saveAll(matches);

		return matches;
	}

	private Optional<Match> pullMatchByMatchId(Region region, String matchId) {
		RiotMatchDto match = apiClient.getMatch(region, matchId);

		if (match == null) {
			log.error("Retrieving match failed for {}", matchId);
			return Optional.empty();
		}

		return Optional.of(riotMapper.mapToMatch(match));
	}

}
