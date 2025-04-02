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
		if (match == null || isMatchArena(match)) {
			return 0;
		}
		Optional<Participant> participant = extractParticipantFromMatch(match, puuid);

		return participant.map(TrophyUtils::calculateKda)
		                  .orElse(0d);
	}
	public static int extractDamageFromMatchForSummoner(Match match, String puuid) {
		if (match == null || isMatchArena(match)) {
			return 0;
		}
		Optional<Participant> participant = extractParticipantFromMatch(match, puuid);

		return participant.map(Participant::getTotalDamageDealtToChampions)
		                  .orElse(0);
	}

	public static int extractTimeSpentDeadFromMatch(Match match, String puuid) {
		if (match == null || isMatchArena(match)) {
			return 0;
		}

		Optional<Participant> participant = extractParticipantFromMatch(match, puuid);
		return participant.map(Participant::getTotalTimeSpentDead).orElse(0);
	}

	public static int extractSpellUsageFromMatch(Match match, String puuid) {
		if (match == null || isMatchArena(match)) {
			return 0;
		}

		Optional<Participant> participant = extractParticipantFromMatch(match, puuid);
		return participant.map(value -> value.getSpell1Casts() + value.getSpell2Casts() + value.getSpell3Casts() + value.getSpell4Casts())
		                  .orElse(0);

	}

	public static int extractCreepScoreFromMatch(Match match, String puuid) {
		if (match == null) {
			return 0;
		}

		Optional<Participant> participant = extractParticipantFromMatch(match, puuid);
		return participant.map(Participant::getTotalMinionsKilled)
		                  .orElse(0);
	}

	public static int extractPingsCountFromMatch(Match match, String puuid) {
		if (match == null) {
			return 0;
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
		                  .orElse(0);
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

	private static boolean isMatchArena(Match match) {
		if (match == null) {
			return false;
		}

		return "CHERRY".equals(match.getGameMode());
	}
}
