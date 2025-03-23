package st.tiy.voidapp.queue.task;

public abstract class VoidTask<T extends VoidTaskParameters> {
	private final T taskParameters;

	protected VoidTask(T taskParameters) {
		this.taskParameters = taskParameters;
	}

	public T getParameters() {
		return this.taskParameters;
	}
}
