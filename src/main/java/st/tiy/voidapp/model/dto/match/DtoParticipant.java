package st.tiy.voidapp.model.dto.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

// Properties ignored in attempt to shorten Jackson serialization times
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({
		"allInPings",
		"assistMePings",
		"baronKills",
		"basicPings",
		"bountyLevel",
		"champExperience",
		"championTransform",
		"commandPings",
		"consumablesPurchased",
		"damageDealtToBuildings",
		"damageDealtToObjectives",
		"damageDealtToTurrets",
		"damageSelfMitigated",
		"dangerPings",
		"detectorWardsPlaced",
		"dragonKills",
		"eligibleForProgression",
		"enemyMissingPings",
		"enemyVisionPings",
		"firstBloodAssist",
		"firstBloodKill",
		"firstTowerAssist",
		"firstTowerKill",
		"gameEndedInEarlySurrender",
		"gameEndedInSurrender",
		"getBackPings",
		"holdPings",
		"individualPosition",
		"inhibitorKills",
		"inhibitorTakedowns",
		"killingSprees",
		"lane",
		"largestKillingSpree",
		"largestMultiKill",
		"longestTimeSpentLiving",
		"magicDamageTaken",
		"needVisionPings",
		"nexusKills",
		"nexusLost",
		"nexusTakedowns",
		"onMyWayPings",
		"physicalDamageTaken",
		"pushPings",
		"role",
		"sightWardsBoughtInGame",
		"spell1Casts",
		"spell2Casts",
		"spell3Casts",
		"spell4Casts",
		"summoner1Casts",
		"summoner2Casts",
		"summonerName",
		"teamEarlySurrendered",
		"timeCCingOthers",
		"timePlayed",
		"totalAllyJungleMinionsKilled",
		"totalDamageShieldedOnTeammates",
		"totalEnemyJungleMinionsKilled",
		"totalHeal",
		"totalTimeCCDealt",
		"totalTimeSpentDead",
		"totalUnitsHealed",
		"trueDamageTaken",
		"turretKills",
		"turretTakedowns",
		"turretsLost",
		"unrealKills",
		"visionClearedPings",
		"visionWardsBoughtInGame",
		"wardsKilled",
		"wardsPlaced"
})
public class DtoParticipant {
	private int allInPings;
	private int assistMePings;
	private int assists;
	private int baronKills;
	private int basicPings;
	private int bountyLevel;
	private DtoChallenges challenges;
	private int champExperience;
	private int champLevel;
	private int championId;
	private String championName;
	private int championTransform;
	private int commandPings;
	private int consumablesPurchased;
	private int damageDealtToBuildings;
	private int damageDealtToObjectives;
	private int damageDealtToTurrets;
	private int damageSelfMitigated;
	private int dangerPings;
	private int deaths;
	private int detectorWardsPlaced;
	private int doubleKills;
	private int dragonKills;
	private boolean eligibleForProgression;
	private int enemyMissingPings;
	private int enemyVisionPings;
	private boolean firstBloodAssist;
	private boolean firstBloodKill;
	private boolean firstTowerAssist;
	private boolean firstTowerKill;
	private boolean gameEndedInEarlySurrender;
	private boolean gameEndedInSurrender;
	private int getBackPings;
	private int goldEarned;
	private int goldSpent;
	private int holdPings;
	private String individualPosition;
	private int inhibitorKills;
	private int inhibitorTakedowns;
	private int inhibitorsLost;
	private int item0;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	private int item6;
	private int itemsPurchased;
	private int killingSprees;
	private int kills;
	private String lane;
	private int largestCriticalStrike;
	private int largestKillingSpree;
	private int largestMultiKill;
	private int longestTimeSpentLiving;
	private int magicDamageDealt;
	private int magicDamageDealtToChampions;
	private int magicDamageTaken;
	private Map<String, Integer> missions;
	private int needVisionPings;
	private int neutralMinionsKilled;
	private int nexusKills;
	private int nexusLost;
	private int nexusTakedowns;
	private int objectivesStolen;
	private int objectivesStolenAssists;
	private int onMyWayPings;
	private int participantId;
	private int pentaKills;
	private DtoPerks perks;
	private int physicalDamageDealt;
	private int physicalDamageDealtToChampions;
	private int physicalDamageTaken;
	private int placement;
	private int playerAugment1;
	private int playerAugment2;
	private int playerAugment3;
	private int playerAugment4;
	private int playerAugment5;
	private int playerAugment6;
	private int playerSubteamId;
	private int profileIcon;
	private int pushPings;
	private String puuid;
	private int quadraKills;
	private String riotIdGameName;
	private String riotIdTagline;
	private String role;
	private int sightWardsBoughtInGame;
	private int spell1Casts;
	private int spell2Casts;
	private int spell3Casts;
	private int spell4Casts;
	private int subteamPlacement;
	private int summoner1Casts;
	private int summoner1Id;
	private int summoner2Casts;
	private int summoner2Id;
	private String summonerId;
	private int summonerLevel;
	private String summonerName;
	private boolean teamEarlySurrendered;
	private int teamId;
	private String teamPosition; // Preferred way to know what role is played
	private int timeCCingOthers;
	private int timePlayed;
	private int totalAllyJungleMinionsKilled;
	private int totalDamageDealt;
	private int totalDamageDealtToChampions;
	private int totalDamageShieldedOnTeammates;
	private int totalDamageTaken;
	private int totalEnemyJungleMinionsKilled;
	private int totalHeal;
	private int totalHealsOnTeammates;
	private int totalMinionsKilled;
	private int totalTimeCCDealt;
	private int totalTimeSpentDead;
	private int totalUnitsHealed;
	private int tripleKills;
	private int trueDamageDealt;
	private int trueDamageDealtToChampions;
	private int trueDamageTaken;
	private int turretKills;
	private int turretTakedowns;
	private int turretsLost;
	private int unrealKills;
	private int visionClearedPings;
	private int visionScore;
	private int visionWardsBoughtInGame;
	private int wardsKilled;
	private int wardsPlaced;
	private boolean win;
}
