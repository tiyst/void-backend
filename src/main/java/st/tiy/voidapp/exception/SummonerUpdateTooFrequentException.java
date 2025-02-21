package st.tiy.voidapp.exception;

import st.tiy.voidapp.model.domain.summoner.Summoner;

import java.time.LocalDateTime;

public class SummonerUpdateTooFrequentException extends RuntimeException {

	public SummonerUpdateTooFrequentException(Summoner summoner, LocalDateTime updateAvailable) {
		super("Summoner %s#%s updated too frequently. Try again in %s".formatted(
				summoner.getGameName(),
				summoner.getTagLine(),
				updateAvailable.toString()
		));
	}
}
