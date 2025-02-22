package st.tiy.voidapp.controller.advice;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiErrorResponse(
		int status,
		String message,
		LocalDateTime timestamp
) {
	public ApiErrorResponse(HttpStatus status, String message) {
		this(status.value(), message, LocalDateTime.now());
	}
}
