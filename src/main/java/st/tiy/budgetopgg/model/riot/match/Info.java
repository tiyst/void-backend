package st.tiy.budgetopgg.model.riot.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Info {
	private String endOfGameResult;
	private long gameCreation;
	private int gameDuration;
	private long gameEndTimestamp;
	private long gameId;
	private String gameMode;
	private String gameName;
	private long gameStartTimestamp;
	private String gameType;
	private String gameVersion;
	private int mapId;
	private ArrayList<Participant> participants;
	private String platformId;
	private int queueId;
	private ArrayList<Team> teams;
	private String tournamentCode;
}
