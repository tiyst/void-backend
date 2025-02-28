package st.tiy.voidapp.exception;

import lombok.Getter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class SummonerUpdateTooFrequentException extends RuntimeException {

	private final String updateAvailableTimestamp;

	public SummonerUpdateTooFrequentException(String message, ZonedDateTime updateAvailableTimestamp) {
		super(message);
		this.updateAvailableTimestamp = updateAvailableTimestamp
				.withZoneSameInstant(ZoneOffset.UTC)
				.format(DateTimeFormatter.ISO_INSTANT);
	}
}
