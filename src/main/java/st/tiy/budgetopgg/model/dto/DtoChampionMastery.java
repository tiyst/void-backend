package st.tiy.budgetopgg.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DtoChampionMastery {

	private Integer championId;
	private Integer championLevel;
	private Integer championPoints;
	private Long lastPlayTime;
	private Integer championPointsSinceLastLevel;
	private Integer championPointsUntilNextLevel;
	private Integer markRequiredForNextLevel;
	private Integer tokensEarned;
	private Integer championSeasonMilestone;
	private List<String> milestoneGrades;
//	private MasteryMilestone masteryMilestone;
}
