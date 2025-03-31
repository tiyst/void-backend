package st.tiy.voidapp.queue.task.trophyroom;

import st.tiy.voidapp.queue.task.VoidTask;
import st.tiy.voidapp.queue.task.VoidTaskPriority;

public class TrophyRoomTask extends VoidTask<TrophyRoomTaskParameters> {

	public TrophyRoomTask(TrophyRoomTaskParameters taskParameters) {
		super(taskParameters, VoidTaskPriority.HIGHER);
	}

}
