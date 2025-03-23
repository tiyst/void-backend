package st.tiy.voidapp.queue;

import org.springframework.stereotype.Service;
import st.tiy.voidapp.queue.task.VoidTask;
import st.tiy.voidapp.queue.task.VoidTaskParameters;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class TaskQueueService {

	private final BlockingQueue<VoidTask<? extends VoidTaskParameters>> queue;

	public TaskQueueService() {
		this.queue = new LinkedBlockingQueue<>();
	}

	public void submitTask(VoidTask<? extends VoidTaskParameters> task) {
		this.queue.add(task);
	}

	public Optional<VoidTask<? extends VoidTaskParameters>> takeTask() {
		return Optional.ofNullable(this.queue.poll());
	}

	public boolean hasTasks() {
		return !this.queue.isEmpty();
	}
}
