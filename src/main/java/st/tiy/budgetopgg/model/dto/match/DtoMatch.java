package st.tiy.budgetopgg.model.dto.match;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DtoMatch {

	private String endOfGameResult;
	private int gameDuration;
	private String gameMode;
	private String gameType;
	private int mapId;

	private List<DtoParticipant> participantList;

	private String platformId;
	private int queueId;

	private List<DtoTeam> teams;

	private LocalDateTime retrievedDate;

}
