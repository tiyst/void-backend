package st.tiy.voidapp.trophy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.domain.summoner.Summoner;
import st.tiy.voidapp.repository.MatchRepository;
import st.tiy.voidapp.trophy.trophies.Trophy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class TrophyRoomService {

	private final TrophyRepository trophyRepository;
	private final MatchRepository matchRepository;
	private final TrophyFactory trophyFactory;

	public TrophyRoomService(TrophyRepository trophyRepository, MatchRepository matchRepository, TrophyFactory trophyFactory) {
		this.trophyRepository = trophyRepository;
		this.matchRepository = matchRepository;
		this.trophyFactory = trophyFactory;
	}

	@Transactional
	public void processMatchesByMatchIds(List<String> matchIds, Summoner summoner) {
		List<Trophy> trophies = getTrophies(summoner);
		List<Match> matches = matchRepository.findAllByMatchIdIsIn(matchIds);

		trophies.forEach(trophy -> compareAndSetNewTrophyIfBetter(trophy, matches));
		trophyRepository.saveAll(trophies);
	}

	public List<Trophy> processMatches(List<Match> matches, Summoner summoner) {
		List<Trophy> trophies = getTrophies(summoner);

		trophies.forEach(trophy -> compareAndSetNewTrophyIfBetter(trophy, matches));
		trophyRepository.saveAll(trophies);

		return trophies;
	}

	public List<Trophy> getTrophies(Summoner summoner) {
		return trophyFactory.getAllTrophyTypes().stream()
		                    .map(trophyType -> trophyRepository.findBySummonerAndType(summoner, trophyType)
		                                                       .orElseGet(() -> trophyFactory.createTrophy(trophyType, summoner)))
		                    .toList();
	}

	private void compareAndSetNewTrophyIfBetter(Trophy trophy, List<Match> newMatches) {
		if (newMatches == null || newMatches.isEmpty()) {
			Summoner summoner = trophy.getSummoner();
			log.warn("No new matches found for summoner {}#{}", summoner.getGameName(), summoner.getTagLine());
			return;
		}

		Comparator<Match> matchComparator = trophy.matchComparator();
		Match bestNewMatch = Collections.max(newMatches, matchComparator);

		if (trophy.isNewMatchBetterThanCurrentBest(bestNewMatch)) {
			trophy.updateBestMatch(bestNewMatch);
		}
	}
}
