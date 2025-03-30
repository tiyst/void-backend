package st.tiy.voidapp.trophy.trophies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import st.tiy.voidapp.model.domain.match.Match;

import java.util.Comparator;

import static st.tiy.voidapp.trophy.TrophyUtils.extractDamageFromMatchForSummoner;

@Slf4j
@Entity
@DiscriminatorValue("HIGHEST_DMG")
public class TrophyHighestDamage extends Trophy {

	public TrophyHighestDamage() {
		super("Highest damage", "Highest damage");
	}

	@Override
	public String getFormattedBestValue(Match match) {
		return extractDamageFromMatchForSummoner(match, summoner.getPuuid()) + " dmg";
	}

	@Override
	public boolean isNewMatchBetterThanCurrentBest(Match newMatch) {
		if (this.bestMatch == null) { return true; }

		return matchComparator().compare(newMatch, this.bestMatch) < 0;
	}

	@Override
	public Comparator<Match> matchComparator() {
		return Comparator.comparingDouble(match -> extractDamageFromMatchForSummoner(match, summoner.getPuuid()));
	}

}
