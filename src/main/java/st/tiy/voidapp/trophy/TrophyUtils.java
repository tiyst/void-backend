package st.tiy.voidapp.trophy;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.domain.match.team.Participant;

import java.util.Optional;

@Slf4j
@UtilityClass
public class TrophyUtils {

	public static double extractKdaFromMatchForSummoner(Match match, String puuid) {
		if (match == null) {
			return 0;
		}
		Optional<Participant> participant = extractParticipantFromMatch(match, puuid);

		return participant.map(TrophyUtils::calculateKda)
		                  .orElse(Double.MIN_VALUE);
	}

	public static int extractTimeSpentDeadFromMatch(Match match, String puuid) {
		if (match == null || isGameArena(match)) {
			return Integer.MIN_VALUE;
		}

		Optional<Participant> participant = extractParticipantFromMatch(match, puuid);
		return participant.map(Participant::getTotalTimeSpentDead).orElse(Integer.MIN_VALUE);
	}

	public static int extractSpellUsageFromMatch(Match match, String puuid) {
		if (match == null || isGameArena(match)) {
			return Integer.MIN_VALUE;
		}

		Optional<Participant> participant = extractParticipantFromMatch(match, puuid);
		return participant.map(value -> value.getSpell1Casts() + value.getSpell2Casts() + value.getSpell3Casts() + value.getSpell4Casts())
		                  .orElse(Integer.MIN_VALUE);

	}

	public static int extractCreepScoreFromMatch(Match match, String puuid) {
		if (match == null) {
			return Integer.MIN_VALUE;
		}

		Optional<Participant> participant = extractParticipantFromMatch(match, puuid);
		return participant.map(Participant::getTotalMinionsKilled)
		                  .orElse(Integer.MIN_VALUE);
	}

	public static int extractPingsCountFromMatch(Match match, String puuid) {
		if (match == null) {
			return Integer.MIN_VALUE;
		}

		Optional<Participant> participant = extractParticipantFromMatch(match, puuid);
		return participant.map(value -> value.getAllInPings()
				                  + value.getAssistMePings()
				                  + value.getBasicPings()
				                  + value.getCommandPings()
				                  + value.getDangerPings()
				                  + value.getEnemyMissingPings()
				                  + value.getEnemyVisionPings()
				                  + value.getGetBackPings()
				                  + value.getHoldPings()
				                  + value.getNeedVisionPings()
				                  + value.getOnMyWayPings()
				                  + value.getPushPings()
				                  + value.getVisionClearedPings())
		                  .orElse(Integer.MIN_VALUE);

	}

	public static Optional<Participant> extractParticipantFromMatch(Match match, String puuid) {
		if (match == null) {
			return Optional.empty();
		}
		return match.getParticipantList().stream()
		            .filter(participant -> puuid.equals(participant.getPuuid()))
		            .findFirst();
	}

	public static double calculateKda(Participant participant) {
		if (participant.getDeaths() == 0) {
			return participant.getKills() + (double) participant.getAssists();
		}
		double kda = (double) (participant.getKills() + participant.getAssists()) / participant.getDeaths();

		return Math.round(kda * 100.0d) / 100.0d;
	}

	private static boolean isGameArena(Match match) {
		if (match == null) {
			return false;
		}

		return "CHERRY".equals(match.getGameMode());
	}
}
