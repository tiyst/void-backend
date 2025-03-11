package st.tiy.voidapp.model.domain.summoner;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import st.tiy.voidapp.model.domain.mastery.ChampionMastery;

import java.util.List;

@Entity
@Table(name = "summoner")
@Getter
@Setter
public class Summoner {

	@Id
	private String puuid;

	private String accountId;
	private String summonerId;
	private long lastUpdated; // Unix epoch in seconds of last time this summoner has been updated

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
