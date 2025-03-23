package st.tiy.voidapp.queue.task;

public enum VoidTaskPriority {
	LOWEST(8),
	LOWER(7),
	LOW(6),
	MEDIUM(5),
	HIGH(4),
	HIGHER(3),
	HIGHEST(2),
	CRITICAL(1),
	BAZOONGA(0);

	public final int priority;

	VoidTaskPriority(int priority) {
		this.priority = priority;
	}

}
