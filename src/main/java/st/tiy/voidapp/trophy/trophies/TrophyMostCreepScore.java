package st.tiy.voidapp.trophy.trophies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import st.tiy.voidapp.model.domain.match.Match;

import java.util.Comparator;

import static st.tiy.voidapp.trophy.TrophyUtils.extractCreepScoreFromMatch;

@Entity
@DiscriminatorValue("MOST_CS")
public class TrophyMostCreepScore extends Trophy {

	public TrophyMostCreepScore() {
		super("Most CS", "Most CS");
	}

	@Override
	public String getFormattedBestValue(Match match) {
		return extractCreepScoreFromMatch(match, summoner.getPuuid()) + " CS";
	}

	@Override
	public boolean isNewMatchBetterThanCurrentBest(Match newMatch) {
		if (this.bestMatch == null) { return true; }

		return matchComparator().compare(newMatch, this.bestMatch) < 0;
	}

	@Override
	public Comparator<Match> matchComparator() {
		return Comparator.comparingInt(match -> extractCreepScoreFromMatch(match, summoner.getPuuid()));
	}
}
