package st.tiy.voidapp.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.queue.task.VoidTask;
import st.tiy.voidapp.queue.task.VoidTaskExecutor;
import st.tiy.voidapp.queue.task.VoidTaskParameters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskProcessingService {

	private final TaskQueueService queueService;
	private final Map<Class<? extends VoidTaskParameters>, VoidTaskExecutor<? extends VoidTaskParameters>> taskExecutors;

	public TaskProcessingService(TaskQueueService queueService,
	                             List<? extends VoidTaskExecutor<? extends VoidTaskParameters>> taskExecutors) {
		this.queueService = queueService;
		this.taskExecutors = constructExecutorMap(taskExecutors);
	}

	@Scheduled(fixedDelayString = "${voidapp.processingQueue.fixedDelay}")
	public void processTasks() {
		if (queueService.hasTasks()) {
			queueService.takeTask().ifPresent(this::executeTask);
		}
	}

	private void executeTask(VoidTask<? extends VoidTaskParameters> task) {
		VoidTaskExecutor<? extends VoidTaskParameters> executor = this.taskExecutors.get(task.getParameters().getClass());
		if (executor == null) {
			log.error("Void task type not recognized {}", task);
			return;
		}

		executeTypedTask(task, executor);
	}

	private <T extends VoidTaskParameters> void executeTypedTask(VoidTask<T> task, VoidTaskExecutor<?> executor) {
		@SuppressWarnings("unchecked")
		VoidTaskExecutor<T> typedExecutor = (VoidTaskExecutor<T>) executor;
		typedExecutor.processTask(task);
	}

	private Map<Class<? extends VoidTaskParameters>, VoidTaskExecutor<? extends VoidTaskParameters>> constructExecutorMap(
			List<? extends VoidTaskExecutor<? extends VoidTaskParameters>> taskExecutors) {
		return taskExecutors.stream().collect(Collectors.toMap(VoidTaskExecutor::getSupportedParametersType, e -> e));
	}
}
