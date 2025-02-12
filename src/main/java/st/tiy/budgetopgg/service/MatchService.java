package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotMatchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import st.tiy.budgetopgg.api.Region;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.mapper.RiotMatchDtoMatchMapper;
import st.tiy.budgetopgg.repository.MatchRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MatchService {

	public static final LocalDateTime MIN_LOCALDATETIME = LocalDateTime.of(2020, 1, 1, 0, 0);

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
		return updateMatchesByPuuid(region, puuid, MIN_LOCALDATETIME);
	}

	public List<Match> updateMatchesByPuuid(Region region, String puuid, LocalDateTime lastMatchTimestamp) {
		String[] matchIds = apiClient.getMatchIds(region, puuid, lastMatchTimestamp);

		List<Match> matches = Arrays.stream(matchIds)
		                            .parallel()
		                            .map(id -> pullMatchByMatchId(region, id))
		                            .filter(Optional::isPresent)
		                            .map(Optional::get)
		                            .toList();

		log.info("Starting save matches");
		matches.forEach(match -> {
			try {
				matchRepository.save(match);
			} catch (DataIntegrityViolationException ex) {
				log.info("Match save {}", ex.getMessage());
			}
		});

		return matches;
	}

	private Optional<Match> pullMatchByMatchId(Region region, String matchId) {
		RiotMatchDto match;

		try {
			match = apiClient.getMatch(region, matchId);
		} catch (HttpClientErrorException e) {
			return Optional.empty();
		}

		return Optional.of(riotMapper.mapToMatch(match));
	}

}
