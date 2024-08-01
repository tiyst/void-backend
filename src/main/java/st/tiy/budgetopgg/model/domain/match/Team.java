package st.tiy.budgetopgg.model.domain.match;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Team {

	@Id
	private long id;

	private int teamId;
	private boolean win;

	@OneToMany
	private List<Participant> participants;
}
