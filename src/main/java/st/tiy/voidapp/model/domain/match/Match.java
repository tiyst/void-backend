package st.tiy.voidapp.model.domain.match;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import st.tiy.voidapp.model.domain.match.team.Participant;
import st.tiy.voidapp.model.domain.match.team.Team;
import st.tiy.voidapp.model.domain.summoner.Summoner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "match",  uniqueConstraints = @UniqueConstraint(columnNames = "matchId"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	@ElementCollection
	private Set<String> trophiedPuuids = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Match match = (Match) o;
		return Objects.equals(matchId, match.matchId);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(matchId);
	}
}
