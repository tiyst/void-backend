 package st.tiy.voidapp.model.domain.match.team;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SuppressWarnings("java:S1104")
public class Challenges {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "participant_id")
	private Participant participant;

	public Integer assistStreakCount12;
	public Integer healFromMapSources;
	public Integer infernalScalePickup;
	public Integer sWARMDefeatAatrox;
	public Integer sWARMDefeatBriar;
	public Integer sWARMDefeatMiniBosses;
	public Integer sWARMEvolveWeapon;
	public Integer sWARMHave3Passives;
	public Integer sWARMKillEnemy;
	public Integer sWARMPickupGold;
	public Integer sWARMReachLevel50;
	public Integer sWARMSurvive15Min;
	public Integer sWARMWinWith5EvolvedWeapons;
	public Integer abilityUses;
	public Integer acesBefore15Minutes;
	public Integer alliedJungleMonsterKills;
	public Integer baronTakedowns;
	public Integer blastConeOppositeOpponentCount;
	public Integer bountyGold;
	public Integer buffsStolen;
	public Integer completeSupportQuestInTime;
	public Integer controlWardsPlaced;
	public Double damagePerMinute;
	public Double damageTakenOnTeamPercentage;
	public Integer dancedWithRiftHerald;
	public Integer deathsByEnemyChamps;
	public Integer dodgeSkillShotsSmallWindow;
	public Integer doubleAces;
	public Integer dragonTakedowns;
	public Integer earlyLaningPhaseGoldExpAdvantage;
	public Integer effectiveHealAndShielding;
	public Integer elderDragonKillsWithOpposingSoul;
	public Integer elderDragonMultikills;
	public Integer enemyChampionImmobilizations;
	public Integer enemyJungleMonsterKills;
	public Integer epicMonsterKillsNearEnemyJungler;
	public Integer epicMonsterKillsWithin30SecondsOfSpawn;
	public Integer epicMonsterSteals;
	public Integer epicMonsterStolenWithoutSmite;
	public Integer firstTurretKilled;
	public Integer fistBumpParticipation;
	public Integer flawlessAces;
	public Integer fullTeamTakedown;
	public Double gameLength;
	public Integer getTakedownsInAllLanesEarlyJungleAsLaner;
	public Double goldPerMinute;
	public Integer hadOpenNexus;
	public Integer immobilizeAndKillWithAlly;
	public Integer initialBuffCount;
	public Integer initialCrabCount;
	public Integer jungleCsBefore10Minutes;
	public Integer junglerTakedownsNearDamagedEpicMonster;
	public Integer kTurretsDestroyedBeforePlatesFall;
	public Integer kda;
	public Integer killAfterHiddenWithAlly;
	public Integer killParticipation;
	public Integer killedChampTookFullTeamDamageSurvived;
	public Integer killingSprees;
	public Integer killsNearEnemyTurret;
	public Integer killsOnOtherLanesEarlyJungleAsLaner;
	public Integer killsOnRecentlyHealedByAramPack;
	public Integer killsUnderOwnTurret;
	public Integer killsWithHelpFromEpicMonster;
	public Integer knockEnemyIntoTeamAndKill;
	public Integer landSkillShotsEarlyGame;
	public Integer laneMinionsFirst10Minutes;
	public Integer laningPhaseGoldExpAdvantage;
	public Integer legendaryCount;
	@ElementCollection
	public List<Integer> legendaryItemUsed;
	public Integer lostAnInhibitor;
	public Integer maxCsAdvantageOnLaneOpponent;
	public Integer maxKillDeficit;
	public Integer maxLevelLeadLaneOpponent;
	public Integer mejaisFullStackInTime;
	public Integer moreEnemyJungleThanOpponent;
	public Integer multiKillOneSpell;
	public Integer multiTurretRiftHeraldCount;
	public Integer multikills;
	public Integer multikillsAfterAggressiveFlash;
	public Integer outerTurretExecutesBefore10Minutes;
	public Integer outnumberedKills;
	public Integer outnumberedNexusKill;
	public Integer perfectDragonSoulsTaken;
	public Integer perfectGame;
	public Integer pickKillWithAlly;
	public Integer playedChampSelectPosition;
	public Integer poroExplosions;
	public Integer quickCleanse;
	public Integer quickFirstTurret;
	public Integer quickSoloKills;
	public Integer riftHeraldTakedowns;
	public Integer saveAllyFromDeath;
	public Integer scuttleCrabKills;
	public Integer skillshotsDodged;
	public Integer skillshotsHit;
	public Integer snowballsHit;
	public Integer soloBaronKills;
	public Integer soloKills;
	public Integer stealthWardsPlaced;
	public Integer survivedSingleDigitHpCount;
	public Integer survivedThreeImmobilizesInFight;
	public Integer takedownOnFirstTurret;
	public Integer takedowns;
	public Integer takedownsAfterGainingLevelAdvantage;
	public Integer takedownsBeforeJungleMinionSpawn;
	public Integer takedownsFirstXMinutes;
	public Integer takedownsInAlcove;
	public Integer takedownsInEnemyFountain;
	public Integer teamBaronKills;
	public Double teamDamagePercentage;
	public Integer teamElderDragonKills;
	public Integer teamRiftHeraldKills;
	public Integer tookLargeDamageSurvived;
	public Integer turretPlatesTaken;
	public Integer turretTakedowns;
	public Integer turretsTakenWithRiftHerald;
	public Integer twentyMinionsIn3SecondsCount;
	public Integer twoWardsOneSweeperCount;
	public Integer unseenRecalls;
	public Double visionScoreAdvantageLaneOpponent;
	public Double visionScorePerMinute;
	public Integer voidMonsterKill;
	public Integer wardTakedowns;
	public Integer wardTakedownsBefore20M;
	public Integer wardsGuarded;
}
