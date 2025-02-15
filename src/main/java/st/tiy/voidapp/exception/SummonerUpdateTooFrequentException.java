package st.tiy.voidapp.exception;

import st.tiy.voidapp.model.domain.summoner.Summoner;

public class SummonerUpdateTooFrequentException extends RuntimeException {

	public SummonerUpdateTooFrequentException(Summoner summoner) {
		super("Summoner %s#%s updated too frequently".formatted(summoner.getGameName(), summoner.getTagLine()));
	}
}
