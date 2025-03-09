package st.tiy.voidapp.model.dto.match;

import java.time.LocalDateTime;
import java.util.List;

public record DtoMatch(
	String endOfGameResult,
	int gameDuration,
	long gameEndTimestamp,
	long gameId,
	String gameMode,
	String gameType,
	int mapId,
	List<DtoParticipant> participants,
	String platformId,
	int queueId,
	List<DtoTeam> teams,
	LocalDateTime retrievedDate
) {
}
