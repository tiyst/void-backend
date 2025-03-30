package st.tiy.voidapp.model.dto;

import lombok.Getter;
import lombok.Setter;
import st.tiy.voidapp.model.dto.match.DtoMatch;

@Getter
@Setter
public class DtoTrophy {

	private String name;
	private String description;
	private DtoMatch bestMatch;
	private String bestValue;

}
