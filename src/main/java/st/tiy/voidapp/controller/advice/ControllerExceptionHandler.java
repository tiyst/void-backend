package st.tiy.voidapp.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import st.tiy.voidapp.exception.SummonerNotFoundException;
import st.tiy.voidapp.exception.SummonerUpdateTooFrequentException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<ApiErrorResponse> handleRestClientException(RestClientException exception) {
		log.error("Error occurred:", exception);

		ApiErrorResponse response = new ApiErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR,
				"Error communicating with riot servers, please try again later."
		);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(SummonerUpdateTooFrequentException.class)
	public ResponseEntity<ApiErrorResponse> handleSummonerUpdateTooFrequentException(SummonerUpdateTooFrequentException exception) {
		log.info("Summoner updating too frequently: {}", exception.toString());

		ApiErrorResponse response = new ApiErrorResponse(
				HttpStatus.TOO_EARLY,
				"Summoner recently updated, please wait."
		);

		return ResponseEntity.status(HttpStatus.TOO_EARLY).body(response);
	}

	@ExceptionHandler(SummonerNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> summonerNotFound(SummonerNotFoundException exception) {
		log.info("Summoner not found: {}", exception.toString());

		ApiErrorResponse response = new ApiErrorResponse(
				HttpStatus.BAD_REQUEST,
				"Summoner not found."
		);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
