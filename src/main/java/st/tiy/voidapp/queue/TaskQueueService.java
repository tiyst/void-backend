package st.tiy.voidapp.queue;

import org.springframework.stereotype.Service;
import st.tiy.voidapp.queue.task.VoidTask;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class TaskQueueService {

	private final BlockingQueue<VoidTask<?>> queue;

	public TaskQueueService() {
		this.queue = new LinkedBlockingQueue<>();
	}

	public void submitTask(VoidTask<?> task) {
		this.queue.add(task);
	}

	public Optional<VoidTask<?>> takeTask() {
		return Optional.ofNullable(this.queue.poll());
	}

	public boolean hasTasks() {
		return !this.queue.isEmpty();
	}
}
