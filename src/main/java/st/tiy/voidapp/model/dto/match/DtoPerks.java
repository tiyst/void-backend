package st.tiy.voidapp.model.dto.match;

import java.util.ArrayList;

public record DtoPerks(int defense,
		int flex,
		int offense,
		ArrayList<DtoPerksStyles> styles
) {
}
