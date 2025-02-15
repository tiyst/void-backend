package st.tiy.voidapp.model.dto.match;

import java.util.List;

public record DtoPerksStyles(String description,
		List<DtoPerksSelection> selections,
		int style) {
}
