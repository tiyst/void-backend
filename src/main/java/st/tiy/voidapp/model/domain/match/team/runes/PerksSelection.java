package st.tiy.voidapp.model.domain.match.team.runes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PerksSelection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int perk;
	private int var1;
	private int var2;
	private int var3;

}
