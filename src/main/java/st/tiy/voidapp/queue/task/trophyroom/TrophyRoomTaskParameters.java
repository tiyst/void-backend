package st.tiy.voidapp.queue.task.trophyroom;

import lombok.Builder;
import lombok.Data;
import st.tiy.voidapp.queue.task.VoidTaskParameters;

import java.util.List;

@Builder
@Data
public class TrophyRoomTaskParameters implements VoidTaskParameters {
	private List<String> matchIds;
	private String puuid;
}
