package st.tiy.voidapp.queue.task.summonerfetch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.queue.task.VoidTask;
import st.tiy.voidapp.queue.task.VoidTaskExecutor;
import st.tiy.voidapp.service.SummonerService;

@Service
public class BasicSummonerProcessExecutor extends VoidTaskExecutor<BasicSummonerProcessTaskParams> {

	private final SummonerService summonerService;
	private final boolean enabled;

	public BasicSummonerProcessExecutor(SummonerService summonerService,
	                                    @Value("${voidapp.processingQueue.basicSummonerFetch:false}") boolean enabled) {
		this.summonerService = summonerService;
		this.enabled = enabled;
	}

	@Override
	public void processTask(VoidTask<BasicSummonerProcessTaskParams> params) {
		if (!enabled) {
			return;
		}

		BasicSummonerProcessTaskParams p = params.getParameters();
		this.summonerService.updateBasicSummoner(p.getServer(), p.getGameName(), p.getTagLine(), p.getMatches());
	}

	@Override
	public Class<BasicSummonerProcessTaskParams> getSupportedParametersType() {
		return BasicSummonerProcessTaskParams.class;
	}
}
