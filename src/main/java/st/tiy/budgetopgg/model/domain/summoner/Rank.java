package st.tiy.budgetopgg.model.domain.summoner;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rank {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "summoner_puuid")
	private Summoner summoner;

	private String leagueId;
	private String queueType;
	private String tier;
	private String division;

	private int leaguePoints;
	private int wins;
	private int losses;

	private boolean freshBlood;
	private boolean hotStreak;
	private boolean veteran;
	private boolean inactive;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private RankSeries rankSeries;

}
