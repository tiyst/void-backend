package st.tiy.budgetopgg.model.dto.match;

import java.time.LocalDateTime;
import java.util.List;

public record DtoMatch(
	String endOfGameResult,
	int gameDuration,
	String gameMode,
	String gameType,
	int mapId,
	List<DtoParticipant> participantList,
	String platformId,
	int queueId,
	List<DtoTeam> teams,
	LocalDateTime retrievedDate
) {
}
