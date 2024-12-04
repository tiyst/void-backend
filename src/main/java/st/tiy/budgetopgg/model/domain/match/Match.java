package st.tiy.budgetopgg.model.domain.match;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import st.tiy.budgetopgg.model.domain.match.team.Participant;
import st.tiy.budgetopgg.model.domain.match.team.Team;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;

@Entity
@Getter
@Setter
public class Match {

	@Id
	private String matchId;

	@ManyToOne(fetch = FetchType.LAZY)
	private Summoner summoner;

	private String dataVersion;
	@ElementCollection
	private List<String> participantIds;

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

	@OneToMany(cascade = CascadeType.ALL)
	private List<Participant> participantList;

	private String platformId;
	private int queueId;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Team> teams;
	private String tournamentCode;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime retrievedDate;
}
