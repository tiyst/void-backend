package st.tiy.voidapp.trophy.trophies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import st.tiy.voidapp.model.domain.match.Match;

import java.util.Comparator;

import static st.tiy.voidapp.trophy.TrophyUtils.extractKdaFromMatchForSummoner;

@Slf4j
@Entity
@DiscriminatorValue("BEST_KDA")
public class TrophyBestKda extends Trophy {

	public TrophyBestKda() {
		super("Best KDA", "Best KDA player has achieved");
	}

	@Override
	public String getFormattedBestValue(Match match) {
		return extractKdaFromMatchForSummoner(match, summoner.getPuuid()) + " KDA";
	}

	@Override
	public boolean isNewMatchBetterThanCurrentBest(Match newMatch) {
		if (this.bestMatch == null) { return true; }

		return matchComparator().compare(newMatch, this.bestMatch) > 0;
	}

	@Override
	public Comparator<Match> matchComparator() {
		return Comparator.comparingDouble(match -> extractKdaFromMatchForSummoner(match, summoner.getPuuid()));
	}

}
