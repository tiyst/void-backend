package st.tiy.voidapp.model.domain.mastery;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import st.tiy.voidapp.model.domain.summoner.Summoner;

import java.util.List;

@Entity
@Getter
@Setter
public class ChampionMastery {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "summoner_puuid")
	private Summoner summoner;

	private Integer championId;
	private Integer championLevel;
	private Integer championPoints;
	private Long lastPlayTime;
	private Integer championPointsSinceLastLevel;
	private Integer championPointsUntilNextLevel;
	private Integer markRequiredForNextLevel;
	private Integer tokensEarned;
	private Integer championSeasonMilestone;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> milestoneGrades;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MasteryMilestone masteryMilestone;
}
