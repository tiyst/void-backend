package st.tiy.budgetopgg.model.dto.match;

import java.util.ArrayList;

public record DtoPerks(int defense,
		int flex,
		int offense,
		ArrayList<DtoPerksStyles> styles
) {
}
