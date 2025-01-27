package st.tiy.budgetopgg.model.dto.match;

import java.util.List;
import java.util.Map;

public record DtoTeam(
	int teamId,
	boolean win,
	Map<String, DtoObjective> objectives,
	List<Integer> championBans // champion IDs banned in order
) {
}
