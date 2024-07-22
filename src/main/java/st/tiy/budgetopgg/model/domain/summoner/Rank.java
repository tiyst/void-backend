package st.tiy.budgetopgg.model.domain.summoner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rank {

	@Id
	@GeneratedValue
	private Long id;

	private String leagueId;
	private String queueType;
	private String tier;
	private String division;

	private int leaguePoints;
	private int wins;
	private int losses;
	private boolean hotStreak;

}
