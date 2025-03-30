package st.tiy.voidapp.trophy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.domain.summoner.Summoner;
import st.tiy.voidapp.trophy.trophies.Trophy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class TrophyRoomService {

	private final TrophyRepository trophyRepository;
	private final TrophyFactory trophyFactory;

	public TrophyRoomService(TrophyRepository trophyRepository, TrophyFactory trophyFactory) {
		this.trophyRepository = trophyRepository;
		this.trophyFactory = trophyFactory;
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
