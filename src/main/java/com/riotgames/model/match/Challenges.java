package com.riotgames.model.match;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Challenges {

	@JsonProperty("12AssistStreakCount")
	public Integer _12AssistStreakCount;
	@JsonProperty("HealFromMapSources")
	public Integer healFromMapSources;
	@JsonProperty("InfernalScalePickup")
	public Integer infernalScalePickup;
	@JsonProperty("SWARM_DefeatAatrox")
	public Integer sWARMDefeatAatrox;
	@JsonProperty("SWARM_DefeatBriar")
	public Integer sWARMDefeatBriar;
	@JsonProperty("SWARM_DefeatMiniBosses")
	public Integer sWARMDefeatMiniBosses;
	@JsonProperty("SWARM_EvolveWeapon")
	public Integer sWARMEvolveWeapon;
	@JsonProperty("SWARM_Have3Passives")
	public Integer sWARMHave3Passives;
	@JsonProperty("SWARM_KillEnemy")
	public Integer sWARMKillEnemy;
	@JsonProperty("SWARM_PickupGold")
	public Integer sWARMPickupGold;
	@JsonProperty("SWARM_ReachLevel50")
	public Integer sWARMReachLevel50;
	@JsonProperty("SWARM_Survive15Min")
	public Integer sWARMSurvive15Min;
	@JsonProperty("SWARM_WinWith5EvolvedWeapons")
	public Integer sWARMWinWith5EvolvedWeapons;
	@JsonProperty("abilityUses")
	public Integer abilityUses;
	@JsonProperty("acesBefore15Minutes")
	public Integer acesBefore15Minutes;
	@JsonProperty("alliedJungleMonsterKills")
	public Integer alliedJungleMonsterKills;
	@JsonProperty("baronTakedowns")
	public Integer baronTakedowns;
	@JsonProperty("blastConeOppositeOpponentCount")
	public Integer blastConeOppositeOpponentCount;
	@JsonProperty("bountyGold")
	public Integer bountyGold;
	@JsonProperty("buffsStolen")
	public Integer buffsStolen;
	@JsonProperty("completeSupportQuestInTime")
	public Integer completeSupportQuestInTime;
	@JsonProperty("controlWardsPlaced")
	public Integer controlWardsPlaced;
	@JsonProperty("damagePerMinute")
	public Double damagePerMinute;
	@JsonProperty("damageTakenOnTeamPercentage")
	public Double damageTakenOnTeamPercentage;
	@JsonProperty("dancedWithRiftHerald")
	public Integer dancedWithRiftHerald;
	@JsonProperty("deathsByEnemyChamps")
	public Integer deathsByEnemyChamps;
	@JsonProperty("dodgeSkillShotsSmallWindow")
	public Integer dodgeSkillShotsSmallWindow;
	@JsonProperty("doubleAces")
	public Integer doubleAces;
	@JsonProperty("dragonTakedowns")
	public Integer dragonTakedowns;
	@JsonProperty("earlyLaningPhaseGoldExpAdvantage")
	public Integer earlyLaningPhaseGoldExpAdvantage;
	@JsonProperty("effectiveHealAndShielding")
	public Integer effectiveHealAndShielding;
	@JsonProperty("elderDragonKillsWithOpposingSoul")
	public Integer elderDragonKillsWithOpposingSoul;
	@JsonProperty("elderDragonMultikills")
	public Integer elderDragonMultikills;
	@JsonProperty("enemyChampionImmobilizations")
	public Integer enemyChampionImmobilizations;
	@JsonProperty("enemyJungleMonsterKills")
	public Integer enemyJungleMonsterKills;
	@JsonProperty("epicMonsterKillsNearEnemyJungler")
	public Integer epicMonsterKillsNearEnemyJungler;
	@JsonProperty("epicMonsterKillsWithin30SecondsOfSpawn")
	public Integer epicMonsterKillsWithin30SecondsOfSpawn;
	@JsonProperty("epicMonsterSteals")
	public Integer epicMonsterSteals;
	@JsonProperty("epicMonsterStolenWithoutSmite")
	public Integer epicMonsterStolenWithoutSmite;
	@JsonProperty("firstTurretKilled")
	public Integer firstTurretKilled;
	@JsonProperty("fistBumpParticipation")
	public Integer fistBumpParticipation;
	@JsonProperty("flawlessAces")
	public Integer flawlessAces;
	@JsonProperty("fullTeamTakedown")
	public Integer fullTeamTakedown;
	@JsonProperty("gameLength")
	public Double gameLength;
	@JsonProperty("getTakedownsInAllLanesEarlyJungleAsLaner")
	public Integer getTakedownsInAllLanesEarlyJungleAsLaner;
	@JsonProperty("goldPerMinute")
	public Double goldPerMinute;
	@JsonProperty("hadOpenNexus")
	public Integer hadOpenNexus;
	@JsonProperty("immobilizeAndKillWithAlly")
	public Integer immobilizeAndKillWithAlly;
	@JsonProperty("initialBuffCount")
	public Integer initialBuffCount;
	@JsonProperty("initialCrabCount")
	public Integer initialCrabCount;
	@JsonProperty("jungleCsBefore10Minutes")
	public Integer jungleCsBefore10Minutes;
	@JsonProperty("junglerTakedownsNearDamagedEpicMonster")
	public Integer junglerTakedownsNearDamagedEpicMonster;
	@JsonProperty("kTurretsDestroyedBeforePlatesFall")
	public Integer kTurretsDestroyedBeforePlatesFall;
	@JsonProperty("kda")
	public Integer kda;
	@JsonProperty("killAfterHiddenWithAlly")
	public Integer killAfterHiddenWithAlly;
	@JsonProperty("killParticipation")
	public Integer killParticipation;
	@JsonProperty("killedChampTookFullTeamDamageSurvived")
	public Integer killedChampTookFullTeamDamageSurvived;
	@JsonProperty("killingSprees")
	public Integer killingSprees;
	@JsonProperty("killsNearEnemyTurret")
	public Integer killsNearEnemyTurret;
	@JsonProperty("killsOnOtherLanesEarlyJungleAsLaner")
	public Integer killsOnOtherLanesEarlyJungleAsLaner;
	@JsonProperty("killsOnRecentlyHealedByAramPack")
	public Integer killsOnRecentlyHealedByAramPack;
	@JsonProperty("killsUnderOwnTurret")
	public Integer killsUnderOwnTurret;
	@JsonProperty("killsWithHelpFromEpicMonster")
	public Integer killsWithHelpFromEpicMonster;
	@JsonProperty("knockEnemyIntoTeamAndKill")
	public Integer knockEnemyIntoTeamAndKill;
	@JsonProperty("landSkillShotsEarlyGame")
	public Integer landSkillShotsEarlyGame;
	@JsonProperty("laneMinionsFirst10Minutes")
	public Integer laneMinionsFirst10Minutes;
	@JsonProperty("laningPhaseGoldExpAdvantage")
	public Integer laningPhaseGoldExpAdvantage;
	@JsonProperty("legendaryCount")
	public Integer legendaryCount;
	@JsonProperty("legendaryItemUsed")
	public List<Integer> legendaryItemUsed;
	@JsonProperty("lostAnInhibitor")
	public Integer lostAnInhibitor;
	@JsonProperty("maxCsAdvantageOnLaneOpponent")
	public Integer maxCsAdvantageOnLaneOpponent;
	@JsonProperty("maxKillDeficit")
	public Integer maxKillDeficit;
	@JsonProperty("maxLevelLeadLaneOpponent")
	public Integer maxLevelLeadLaneOpponent;
	@JsonProperty("mejaisFullStackInTime")
	public Integer mejaisFullStackInTime;
	@JsonProperty("moreEnemyJungleThanOpponent")
	public Integer moreEnemyJungleThanOpponent;
	@JsonProperty("multiKillOneSpell")
	public Integer multiKillOneSpell;
	@JsonProperty("multiTurretRiftHeraldCount")
	public Integer multiTurretRiftHeraldCount;
	@JsonProperty("multikills")
	public Integer multikills;
	@JsonProperty("multikillsAfterAggressiveFlash")
	public Integer multikillsAfterAggressiveFlash;
	@JsonProperty("outerTurretExecutesBefore10Minutes")
	public Integer outerTurretExecutesBefore10Minutes;
	@JsonProperty("outnumberedKills")
	public Integer outnumberedKills;
	@JsonProperty("outnumberedNexusKill")
	public Integer outnumberedNexusKill;
	@JsonProperty("perfectDragonSoulsTaken")
	public Integer perfectDragonSoulsTaken;
	@JsonProperty("perfectGame")
	public Integer perfectGame;
	@JsonProperty("pickKillWithAlly")
	public Integer pickKillWithAlly;
	@JsonProperty("playedChampSelectPosition")
	public Integer playedChampSelectPosition;
	@JsonProperty("poroExplosions")
	public Integer poroExplosions;
	@JsonProperty("quickCleanse")
	public Integer quickCleanse;
	@JsonProperty("quickFirstTurret")
	public Integer quickFirstTurret;
	@JsonProperty("quickSoloKills")
	public Integer quickSoloKills;
	@JsonProperty("riftHeraldTakedowns")
	public Integer riftHeraldTakedowns;
	@JsonProperty("saveAllyFromDeath")
	public Integer saveAllyFromDeath;
	@JsonProperty("scuttleCrabKills")
	public Integer scuttleCrabKills;
	@JsonProperty("skillshotsDodged")
	public Integer skillshotsDodged;
	@JsonProperty("skillshotsHit")
	public Integer skillshotsHit;
	@JsonProperty("snowballsHit")
	public Integer snowballsHit;
	@JsonProperty("soloBaronKills")
	public Integer soloBaronKills;
	@JsonProperty("soloKills")
	public Integer soloKills;
	@JsonProperty("stealthWardsPlaced")
	public Integer stealthWardsPlaced;
	@JsonProperty("survivedSingleDigitHpCount")
	public Integer survivedSingleDigitHpCount;
	@JsonProperty("survivedThreeImmobilizesInFight")
	public Integer survivedThreeImmobilizesInFight;
	@JsonProperty("takedownOnFirstTurret")
	public Integer takedownOnFirstTurret;
	@JsonProperty("takedowns")
	public Integer takedowns;
	@JsonProperty("takedownsAfterGainingLevelAdvantage")
	public Integer takedownsAfterGainingLevelAdvantage;
	@JsonProperty("takedownsBeforeJungleMinionSpawn")
	public Integer takedownsBeforeJungleMinionSpawn;
	@JsonProperty("takedownsFirstXMinutes")
	public Integer takedownsFirstXMinutes;
	@JsonProperty("takedownsInAlcove")
	public Integer takedownsInAlcove;
	@JsonProperty("takedownsInEnemyFountain")
	public Integer takedownsInEnemyFountain;
	@JsonProperty("teamBaronKills")
	public Integer teamBaronKills;
	@JsonProperty("teamDamagePercentage")
	public Double teamDamagePercentage;
	@JsonProperty("teamElderDragonKills")
	public Integer teamElderDragonKills;
	@JsonProperty("teamRiftHeraldKills")
	public Integer teamRiftHeraldKills;
	@JsonProperty("tookLargeDamageSurvived")
	public Integer tookLargeDamageSurvived;
	@JsonProperty("turretPlatesTaken")
	public Integer turretPlatesTaken;
	@JsonProperty("turretTakedowns")
	public Integer turretTakedowns;
	@JsonProperty("turretsTakenWithRiftHerald")
	public Integer turretsTakenWithRiftHerald;
	@JsonProperty("twentyMinionsIn3SecondsCount")
	public Integer twentyMinionsIn3SecondsCount;
	@JsonProperty("twoWardsOneSweeperCount")
	public Integer twoWardsOneSweeperCount;
	@JsonProperty("unseenRecalls")
	public Integer unseenRecalls;
	@JsonProperty("visionScoreAdvantageLaneOpponent")
	public Double visionScoreAdvantageLaneOpponent;
	@JsonProperty("visionScorePerMinute")
	public Double visionScorePerMinute;
	@JsonProperty("voidMonsterKill")
	public Integer voidMonsterKill;
	@JsonProperty("wardTakedowns")
	public Integer wardTakedowns;
	@JsonProperty("wardTakedownsBefore20M")
	public Integer wardTakedownsBefore20M;
	@JsonProperty("wardsGuarded")
	public Integer wardsGuarded;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<>();

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
