package st.tiy.voidapp.trophy.trophies;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.domain.summoner.Summoner;

import java.util.Comparator;

@Getter
@Entity
@Table(name = "trophies")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "trophy_type")
public abstract class Trophy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;

	@Setter
	@ManyToOne
	@JoinColumn(name = "summoner_id", nullable = false)
	protected Summoner summoner;

	@ManyToOne
	@JoinColumn(name = "best_match_id", referencedColumnName = "matchId")
	protected Match bestMatch;

	protected String bestValue;

	protected Trophy(String name, String description) {
		this.name = name;
		this.description = description;
	}

	protected Trophy() {} // For JPA

	public void updateBestMatch(Match newMatch) {
		if (bestMatch == null || !isNewMatchBetterThanCurrentBest(newMatch)) {
			if (bestMatch != null) {
				this.bestMatch.getTrophiedPuuids().remove(summoner.getPuuid());
			}
			this.bestMatch = newMatch;
			this.bestValue = getFormattedBestValue(newMatch);
			newMatch.getTrophiedPuuids().add(summoner.getPuuid());
		}
	}

	public abstract String getFormattedBestValue(Match match);

	public abstract boolean isNewMatchBetterThanCurrentBest(Match newMatch);

	public abstract Comparator<Match> matchComparator();

}
