package st.tiy.voidapp.queue.task.trophyroom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.queue.task.VoidTask;
import st.tiy.voidapp.queue.task.VoidTaskExecutor;
import st.tiy.voidapp.trophy.TrophyRoomService;

@Service
public class TrophyRoomTaskExecutor extends VoidTaskExecutor<TrophyRoomTaskParameters> {

	private final TrophyRoomService trophyRoomService;
	private final boolean enabled;

	public TrophyRoomTaskExecutor(TrophyRoomService trophyRoomService,
	                              @Value("${voidapp.processingQueue.trophyRoomProcessing:false}") boolean enabled) {
		this.trophyRoomService = trophyRoomService;
		this.enabled = enabled;
	}

	@Override
	public void processTask(VoidTask<TrophyRoomTaskParameters> params) {
		if (!enabled) {
			return;
		}

		TrophyRoomTaskParameters parameters = params.getParameters();
		trophyRoomService.processMatches(parameters.getMatches(), parameters.getSummoner());
	}

	@Override
	public Class<TrophyRoomTaskParameters> getSupportedParametersType() {
		return TrophyRoomTaskParameters.class;
	}

}
