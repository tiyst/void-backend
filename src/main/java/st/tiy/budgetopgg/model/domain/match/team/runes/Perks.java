package st.tiy.budgetopgg.model.domain.match.team.runes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Perks {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int defense;
	private int flex;
	private int offense;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PerksStyle> styles;
}
