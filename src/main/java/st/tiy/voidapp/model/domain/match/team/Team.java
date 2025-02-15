package st.tiy.voidapp.model.domain.match.team;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int teamId;
	private boolean win;

	@OneToMany(cascade = CascadeType.ALL)
	private Map<String, Objective> objectives;

	// champion IDs banned in order
	@ElementCollection
	private List<Integer> championBans;

}
