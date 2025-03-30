package st.tiy.voidapp.queue.task.trophyroom;

import lombok.Data;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.domain.summoner.Summoner;
import st.tiy.voidapp.queue.task.VoidTaskParameters;

import java.util.List;

@Data
public class TrophyRoomTaskParameters implements VoidTaskParameters {
	private Summoner summoner;
	private List<Match> matches;
}
