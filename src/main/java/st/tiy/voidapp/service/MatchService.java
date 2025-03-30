package st.tiy.voidapp.service;

import com.riotgames.model.RiotMatchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import st.tiy.voidapp.api.Region;
import st.tiy.voidapp.api.RiotApiClient;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.dto.mapper.DtoMatchMapper;
import st.tiy.voidapp.model.dto.match.DtoMatch;
import st.tiy.voidapp.model.mapper.RiotMatchDtoMatchMapper;
import st.tiy.voidapp.repository.MatchRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MatchService {

	private final MatchRepository matchRepository;
	private final RiotMatchDtoMatchMapper riotMapper;
	private final DtoMatchMapper dtoMatchMapper;
	private final RiotApiClient apiClient;

	public MatchService(MatchRepository matchRepository,
	                    RiotMatchDtoMatchMapper riotMapper,
	                    DtoMatchMapper dtoMatchMapper,
	                    RiotApiClient apiClient) {
		this.matchRepository = matchRepository;
		this.riotMapper = riotMapper;
		this.dtoMatchMapper = dtoMatchMapper;
		this.apiClient = apiClient;
	}

	public List<Match> getInitialMatchesByPuuid(String puuid) {
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "gameEndTimestamp"));
		return matchRepository.findByParticipantIdsContaining(puuid, pageable).getContent();
	}

	public List<Match> updateMatchesByPuuid(Region region, String puuid) {
		String[] matchIds = apiClient.getMatchIds(region, puuid);
		List<String> existingMatches = matchRepository.findAllByMatchIdIsIn(List.of(matchIds))
		                                              .stream()
		                                              .map(Match::getMatchId)
		                                              .toList();

		List<Match> matches = Arrays.stream(matchIds)
		                            .filter(id -> !existingMatches.contains(id))
		                            .parallel()
		                            .map(id -> pullMatchByMatchId(region, id))
		                            .filter(Optional::isPresent)
		                            .map(Optional::get)
		                            .toList();

		log.info("Starting save matches");
		matchRepository.saveAllAndFlush(matches);
		log.info("Finished save matches");
		return matches;
	}

	public List<DtoMatch> getPageableMatchesByPuuid(String puuid, int retrievedMatchesCount) {
		Pageable pageable = PageRequest.of(retrievedMatchesCount / 10, 10, Sort.by(Sort.Direction.DESC, "gameEndTimestamp"));
		List<Match> matches = matchRepository.findByParticipantIdsContaining(puuid, pageable).getContent();

		return dtoMatchMapper.toDtoMatches(matches);
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
