package st.tiy.voidapp.model.domain.match.team;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import st.tiy.voidapp.model.domain.match.team.runes.Perks;

import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int allInPings;
	private int assistMePings;
	private int assists;
	private int baronKills;
	private int basicPings;
	private int bountyLevel;
	@OneToOne(cascade = CascadeType.ALL)
	private Challenges challenges;
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
	@ElementCollection
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
	@OneToOne(cascade = CascadeType.ALL)
	private Perks perks;
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
	private String teamPosition;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Participant that = (Participant) obj;
		return Objects.equals(puuid, that.puuid); // Assuming puuid uniquely identifies a participant
	}

	@Override
	public int hashCode() {
		return Objects.hash(puuid);
	}
}
