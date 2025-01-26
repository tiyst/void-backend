package st.tiy.budgetopgg.model.dto.match;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DtoTeam {

	private int teamId;
	private boolean win;

	private Map<String, DtoObjective> objectives;
	// champion IDs banned in order
	private List<Integer> championBans;
}
