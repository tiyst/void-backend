package st.tiy.voidapp.utils;

import lombok.experimental.UtilityClass;
import st.tiy.voidapp.model.domain.summoner.Summoner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class SummonerUtils {

	public static LocalDateTime transformLastUpdateFlag(long lastUpdated) {
		Instant instant = Instant.ofEpochSecond(lastUpdated);
		return LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
	}

	public static boolean isSummonerEligibleForUpdate(Summoner summoner, int minDaysThreshold) {
		LocalDateTime lastUpdated = transformLastUpdateFlag(summoner.getLastUpdated());
		LocalDateTime now = LocalDateTime.now();

		return now.minusDays(minDaysThreshold).isAfter(lastUpdated);
	}
}
