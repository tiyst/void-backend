package st.tiy.budgetopgg.exception;

public class MissingApiKeyException extends RuntimeException {

	public MissingApiKeyException() {
		super("Missing api key");
	}

	public MissingApiKeyException(String message) {
		super(message);
	}
}
