package st.tiy.voidapp.trophy.trophies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.domain.match.team.Participant;
import st.tiy.voidapp.trophy.TrophyUtils;

import java.util.Comparator;
import java.util.Optional;

@Slf4j
@Entity
@DiscriminatorValue("MOST_DEATHS")
public class TrophyMostDeaths extends Trophy {

	public TrophyMostDeaths() {
		super("Most deaths", "Most deaths player has achieved in a game");
	}

	@Override
	public String getFormattedBestValue(Match match) {
		return getDeathsFromMatchForPuuid(match, summoner.getPuuid()) + " deaths";
	}

	@Override
	public boolean isNewMatchBetterThanCurrentBest(Match newMatch) {
		if (this.bestMatch == null) {
			return true;
		}

		return matchComparator().compare(newMatch, this.bestMatch) < 0;
	}

	@Override
	public Comparator<Match> matchComparator() {
		return Comparator.comparingInt(match -> getDeathsFromMatchForPuuid(match, summoner.getPuuid()));
	}

	private int getDeathsFromMatchForPuuid(Match match, String puuid) {
		Optional<Participant> player = TrophyUtils.extractParticipantFromMatch(match, puuid);
		if (player.isEmpty()) {
			log.warn("No participant found for puuid: {} int match: {} ", puuid, match.getMatchId());
			return 0;
		}

		return player.get().getDeaths();
	}
}
