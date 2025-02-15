package st.tiy.voidapp.model.dto;

import java.util.List;

public record DtoChampionMastery(
	Integer championId,
	Integer championLevel,
	Integer championPoints,
	Long lastPlayTime,
	Integer championPointsSinceLastLevel,
	Integer championPointsUntilNextLevel,
	Integer markRequiredForNextLevel,
	Integer tokensEarned,
	Integer championSeasonMilestone,
	List<String> milestoneGrades
//	MasteryMilestone masteryMilestone;
) {
}
