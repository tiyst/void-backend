package st.tiy.voidapp.controller.advice;

import org.springframework.http.HttpStatus;

public record ApiErrorResponse(
		int status,
		String message,
		String timestamp
) {
	public ApiErrorResponse(HttpStatus status, String message) {
		this(status.value(), message, null);
	}

	public ApiErrorResponse(HttpStatus status, String message, String timestamp) {
		this(status.value(), message, timestamp);
	}
}
