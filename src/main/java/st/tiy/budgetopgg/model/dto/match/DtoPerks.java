package st.tiy.budgetopgg.model.dto.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class DtoPerks {

	private int defense;
	private int flex;
	private int offense;
	private ArrayList<DtoPerksStyles> styles;
}
