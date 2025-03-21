package st.tiy.voidapp.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.queue.task.VoidTask;
import st.tiy.voidapp.queue.task.summonerfetch.BasicSummonerProcessTask;
import st.tiy.voidapp.queue.task.summonerfetch.BasicSummonerProcessTaskParams;
import st.tiy.voidapp.service.SummonerService;

@Slf4j
@Service
@ConditionalOnProperty(value = "voidapp.processingQueue.enabled", havingValue = "true")
public class TaskProcessingService {

	private final TaskQueueService queueService;
	private final SummonerService summonerService;

	public TaskProcessingService(TaskQueueService queueService, SummonerService summonerService) {
		this.queueService = queueService;
		this.summonerService = summonerService;
	}

	@Scheduled(fixedDelayString = "${voidapp.processingQueue.fixedDelay}")
	public void processTasks() {
		if (queueService.hasTasks()) {
			queueService.takeTask().ifPresent(this::executeTask);
		}
	}

	private void executeTask(VoidTask<?> task) {
		switch (task) {
			case BasicSummonerProcessTask processTask -> {
				BasicSummonerProcessTaskParams p = processTask.getParameters();
				this.summonerService.updateBasicSummoner(p.server(), p.gameName(), p.tagLine());
			}
			default -> log.error("Void task type not recognized {}", task);
		}
	}
}
