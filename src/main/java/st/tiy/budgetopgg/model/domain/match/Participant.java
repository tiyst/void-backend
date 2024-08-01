package st.tiy.budgetopgg.model.domain.match;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;


@Getter
@Setter
@Entity
public class Participant {

	@Id
	private Long id;

	@ManyToOne
	private Summoner summoner;

	@ManyToOne
	private Team team;

	//private int teamId;
	private String lane;
	private String role;
	private int championId;
	private String championName;
	private int bannedChampionId;
	// sum. spells
	private int spell1Id;
	private int spell2Id;
	// KDA info
	private int assists;
	private int kills;
	private int deaths;
	private int largestKillingSpree;

	// TODO add if summoner left early? private boolean hasLeft;

	// TODO List<items> instead?
	private int item0;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	private int item6;

	// participant's runes TODO map?
	private int playerAugment1;
	private int playerAugment2;
	private int playerAugment3;
	private int playerAugment4;
	private int playerAugment5;
	private int playerAugment6;
}
