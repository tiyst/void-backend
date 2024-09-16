package st.tiy.budgetopgg.model.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Match {

	@Id
	private Long id;

	@ElementCollection
	private List<String> playersPuuids;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public List<String> getPlayersPuuids() {
		return playersPuuids;
	}

	public void setPlayersPuuids(List<String> playersPuuids) {
		this.playersPuuids = playersPuuids;
	}
}
