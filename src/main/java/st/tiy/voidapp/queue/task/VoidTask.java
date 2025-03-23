package st.tiy.voidapp.queue.task;

import java.util.Objects;

public abstract class VoidTask<T extends VoidTaskParameters> implements Comparable<VoidTask<?>> {
	private final T taskParameters;
	private final VoidTaskPriority priority;

	protected VoidTask(T taskParameters, VoidTaskPriority priority) {
		this.taskParameters = taskParameters;
		this.priority = priority;
	}

	public T getParameters() {
		return this.taskParameters;
	}

	public VoidTaskPriority getPriority() {
		return this.priority;
	}

	@Override
	public int compareTo(VoidTask<?> other) {
		return Integer.compare(this.priority.priority, other.priority.priority);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		VoidTask<?> other = (VoidTask<?>) obj;
		return Objects.equals(this.taskParameters, other.getParameters()) && this.priority == other.getPriority();
	}

	@Override
	public int hashCode() {
		return Objects.hash(taskParameters, priority);
	}
}
