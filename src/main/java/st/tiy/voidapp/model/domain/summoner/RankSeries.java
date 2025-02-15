package st.tiy.voidapp.model.domain.summoner;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RankSeries {

	@Id
	private Long id;

	@OneToOne
	@JoinColumn(name = "rank_id")
	private Rank rank;

	private int losses;
	private String progress;
	private int target;
	private int wins;

}
