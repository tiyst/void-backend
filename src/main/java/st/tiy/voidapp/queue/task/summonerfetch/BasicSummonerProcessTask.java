package st.tiy.voidapp.queue.task.summonerfetch;

import lombok.extern.slf4j.Slf4j;
import st.tiy.voidapp.queue.task.VoidTask;
import st.tiy.voidapp.queue.task.VoidTaskPriority;

@Slf4j
public class BasicSummonerProcessTask extends VoidTask<BasicSummonerProcessTaskParams> {

	public BasicSummonerProcessTask(BasicSummonerProcessTaskParams parameters) {
		super(parameters, VoidTaskPriority.MEDIUM);
	}

}
