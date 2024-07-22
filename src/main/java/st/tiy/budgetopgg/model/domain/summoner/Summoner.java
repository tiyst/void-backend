package st.tiy.budgetopgg.model.domain.summoner;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Summoner {

	@Id
	private String puuid;

	private String accountId;
	private String id;
	private long lastUpdated; // Unix epoch of last time this summoner has been updated

	private String gameName;
	private String tagLine;
	private String server;
	private int profileIcon;
	private long level;

	@OneToMany
	private List<Rank> rank;

}
