package st.tiy.voidapp.trophy.trophies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import st.tiy.voidapp.model.domain.match.Match;

import java.util.Comparator;

import static st.tiy.voidapp.trophy.TrophyUtils.extractTimeSpentDeadFromMatch;

@Entity
@DiscriminatorValue("LONGEST_TIME_SPENT_DEAD")
public class TrophyLongestTimeSpentDead extends Trophy {

	public TrophyLongestTimeSpentDead() {
		super("Longest time spent dead", "Longest time spent dead");
	}

	@Override
	public String getFormattedBestValue(Match match) {
		return extractTimeSpentDeadFromMatch(match, summoner.getPuuid()) + " seconds";
	}

	@Override
	public boolean isNewMatchBetterThanCurrentBest(Match newMatch) {
		if (this.bestMatch == null) { return true; }

		return matchComparator().compare(newMatch, this.bestMatch) > 0;
	}

	@Override
	public Comparator<Match> matchComparator() {
		return Comparator.comparingInt(match -> extractTimeSpentDeadFromMatch(match, summoner.getPuuid()));
	}
}
