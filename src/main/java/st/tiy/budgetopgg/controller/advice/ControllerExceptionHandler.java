package st.tiy.budgetopgg.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import st.tiy.budgetopgg.exception.SummonerUpdateTooFrequentException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Riot servers are down, please try again later")
	@ExceptionHandler(RestClientException.class)
	public void handleRestClientException(RestClientException exception) {
		log.error("Error occurred: %s", exception);
	}

	@ResponseStatus(value = HttpStatus.TOO_EARLY, reason = "Summoner recently updated, please wait")
	@ExceptionHandler(SummonerUpdateTooFrequentException.class)
	public void handleSummonerUpdateTooFrequentException(SummonerUpdateTooFrequentException exception) {
		log.info("Summoner updating too frequently: %s", exception.toString());
	}
}
