package st.tiy.voidapp.trophy.trophies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import st.tiy.voidapp.model.domain.match.Match;

import java.util.Comparator;

import static st.tiy.voidapp.trophy.TrophyUtils.extractPingsCountFromMatch;
import static st.tiy.voidapp.trophy.TrophyUtils.extractSpellUsageFromMatch;

@Entity
@DiscriminatorValue("MOST_PINGS")
public class TrophyMostPings extends Trophy {

	public TrophyMostPings() {
		super("Most pings", "Most pings");
	}

	@Override
	public String getFormattedBestValue(Match match) {
		return extractSpellUsageFromMatch(match, summoner.getPuuid()) + " pings";
	}


	@Override
	public boolean isNewMatchBetterThanCurrentBest(Match newMatch) {
		if (this.bestMatch == null) { return true; }

		return matchComparator().compare(newMatch, this.bestMatch) < 0;
	}

	@Override
	public Comparator<Match> matchComparator() {
		return Comparator.comparingInt(match -> extractPingsCountFromMatch(match, summoner.getPuuid()));
	}
}
