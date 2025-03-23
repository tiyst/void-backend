package st.tiy.voidapp.queue.task;

public abstract class VoidTaskExecutor<T extends VoidTaskParameters> {

	public abstract void processTask(VoidTask<T> params);

	public abstract Class<T> getSupportedParametersType();

}
