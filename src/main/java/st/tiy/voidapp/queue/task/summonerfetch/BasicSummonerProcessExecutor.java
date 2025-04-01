package st.tiy.voidapp.queue.task.summonerfetch;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.queue.task.VoidTask;
import st.tiy.voidapp.queue.task.VoidTaskExecutor;
import st.tiy.voidapp.service.SummonerService;

@Service
@ConditionalOnProperty(value = "voidapp.processingQueue.basicSummonerFetch", havingValue = "true")
public class BasicSummonerProcessExecutor extends VoidTaskExecutor<BasicSummonerProcessTaskParams> {

	private final SummonerService summonerService;

	public BasicSummonerProcessExecutor(SummonerService summonerService) {
		this.summonerService = summonerService;
	}

	@Override
	public void processTask(VoidTask<BasicSummonerProcessTaskParams> params) {
		BasicSummonerProcessTaskParams p = params.getParameters();
		this.summonerService.updateBasicSummoner(p.getServer(), p.getGameName(), p.getTagLine(), p.getMatches());
	}

	@Override
	public Class<BasicSummonerProcessTaskParams> getSupportedParametersType() {
		return BasicSummonerProcessTaskParams.class;
	}
}
