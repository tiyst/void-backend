package st.tiy.budgetopgg.model.domain.mastery;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
public class MasteryMilestone {

	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection
	private Map<String, Integer> requireGradeCounts;
	private Integer rewardMarks;
	private Boolean bonus;
	private Integer totalGamesRequires;

}
