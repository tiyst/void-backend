package st.tiy.voidapp.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.domain.match.team.Participant;
import st.tiy.voidapp.model.domain.match.team.runes.Perks;
import st.tiy.voidapp.model.dto.match.DtoMatch;
import st.tiy.voidapp.model.dto.match.DtoParticipant;
import st.tiy.voidapp.model.dto.match.DtoPerks;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoMatchMapper {

	List<DtoMatch> toDtoMatches(List<Match> matches);

	List<DtoParticipant> toDtoParticipants(List<Participant> participants);

	@Mapping(source = "participantList", target = "participants")
	DtoMatch toDtoMatch(Match match);

	DtoPerks toDtoPerks(Perks perks);

	@Mapping(target = "allInPings", ignore = true)
	@Mapping(target = "assistMePings", ignore = true)
	@Mapping(target = "baronKills", ignore = true)
	@Mapping(target = "basicPings", ignore = true)
	@Mapping(target = "bountyLevel", ignore = true)
	@Mapping(target = "challenges", ignore = true)
	@Mapping(target = "champExperience", ignore = true)
	@Mapping(target = "championTransform", ignore = true)
	@Mapping(target = "commandPings", ignore = true)
	@Mapping(target = "consumablesPurchased", ignore = true)
	@Mapping(target = "damageDealtToBuildings", ignore = true)
	@Mapping(target = "damageDealtToObjectives", ignore = true)
	@Mapping(target = "damageDealtToTurrets", ignore = true)
	@Mapping(target = "damageSelfMitigated", ignore = true)
	@Mapping(target = "dangerPings", ignore = true)
	@Mapping(target = "detectorWardsPlaced", ignore = true)
	@Mapping(target = "dragonKills", ignore = true)
	@Mapping(target = "eligibleForProgression", ignore = true)
	@Mapping(target = "enemyMissingPings", ignore = true)
	@Mapping(target = "enemyVisionPings", ignore = true)
	@Mapping(target = "firstBloodAssist", ignore = true)
	@Mapping(target = "firstBloodKill", ignore = true)
	@Mapping(target = "firstTowerAssist", ignore = true)
	@Mapping(target = "firstTowerKill", ignore = true)
	@Mapping(target = "gameEndedInEarlySurrender", ignore = true)
	@Mapping(target = "gameEndedInSurrender", ignore = true)
	@Mapping(target = "getBackPings", ignore = true)
	@Mapping(target = "holdPings", ignore = true)
	@Mapping(target = "individualPosition", ignore = true)
	@Mapping(target = "inhibitorKills", ignore = true)
	@Mapping(target = "inhibitorTakedowns", ignore = true)
	@Mapping(target = "inhibitorsLost", ignore = true)
	@Mapping(target = "killingSprees", ignore = true)
	@Mapping(target = "lane", ignore = true)
	@Mapping(target = "largestCriticalStrike", ignore = true)
	@Mapping(target = "largestKillingSpree", ignore = true)
	@Mapping(target = "largestMultiKill", ignore = true)
	@Mapping(target = "longestTimeSpentLiving", ignore = true)
	@Mapping(target = "magicDamageTaken", ignore = true)
	@Mapping(target = "needVisionPings", ignore = true)
	@Mapping(target = "nexusKills", ignore = true)
	@Mapping(target = "nexusLost", ignore = true)
	@Mapping(target = "nexusTakedowns", ignore = true)
	@Mapping(target = "objectivesStolen", ignore = true)
	@Mapping(target = "objectivesStolenAssists", ignore = true)
	@Mapping(target = "onMyWayPings", ignore = true)
	@Mapping(target = "physicalDamageTaken", ignore = true)
	@Mapping(target = "pushPings", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "sightWardsBoughtInGame", ignore = true)
	@Mapping(target = "spell1Casts", ignore = true)
	@Mapping(target = "spell2Casts", ignore = true)
	@Mapping(target = "spell3Casts", ignore = true)
	@Mapping(target = "spell4Casts", ignore = true)
	@Mapping(target = "summoner1Casts", ignore = true)
	@Mapping(target = "summoner2Casts", ignore = true)
	@Mapping(target = "summonerName", ignore = true)
	@Mapping(target = "teamEarlySurrendered", ignore = true)
	@Mapping(target = "timeCCingOthers", ignore = true)
	@Mapping(target = "timePlayed", ignore = true)
	@Mapping(target = "totalAllyJungleMinionsKilled", ignore = true)
	@Mapping(target = "totalDamageShieldedOnTeammates", ignore = true)
	@Mapping(target = "totalDamageTaken", ignore = true)
	@Mapping(target = "totalEnemyJungleMinionsKilled", ignore = true)
	@Mapping(target = "totalHeal", ignore = true)
	@Mapping(target = "totalHealsOnTeammates", ignore = true)
	@Mapping(target = "totalTimeCCDealt", ignore = true)
	@Mapping(target = "totalTimeSpentDead", ignore = true)
	@Mapping(target = "totalUnitsHealed", ignore = true)
	@Mapping(target = "trueDamageTaken", ignore = true)
	@Mapping(target = "turretKills", ignore = true)
	@Mapping(target = "turretTakedowns", ignore = true)
	@Mapping(target = "turretsLost", ignore = true)
	@Mapping(target = "unrealKills", ignore = true)
	@Mapping(target = "visionClearedPings", ignore = true)
	@Mapping(target = "visionWardsBoughtInGame", ignore = true)
	@Mapping(target = "wardsKilled", ignore = true)
	@Mapping(target = "wardsPlaced", ignore = true)
	DtoParticipant toDtoParticipant(Participant participant);
}
