package st.tiy.budgetopgg.model.domain.summoner;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import st.tiy.budgetopgg.model.domain.mastery.ChampionMastery;

import java.util.List;

@Entity
@Getter
@Setter
public class Summoner {

	@Id
	private String puuid;

	private String accountId;
	private String summonerId;
	private long lastUpdated; // Unix epoch of last time this summoner has been updated

	private String gameName;
	private String tagLine;
	private String server;
	private int profileIcon;
	private long level;

	@OneToMany(mappedBy = "summoner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rank> rank;

	@OneToMany(mappedBy = "summoner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChampionMastery> masteries;

}
