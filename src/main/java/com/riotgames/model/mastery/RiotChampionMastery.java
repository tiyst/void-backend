package com.riotgames.model.mastery;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RiotChampionMastery {

	@JsonProperty("puuid")
	public String puuid;
	@JsonProperty("championId")
	public Integer championId;
	@JsonProperty("championLevel")
	public Integer championLevel;
	@JsonProperty("championPoints")
	public Integer championPoints;
	@JsonProperty("lastPlayTime")
	public Long lastPlayTime;
	@JsonProperty("championPointsSinceLastLevel")
	public Integer championPointsSinceLastLevel;
	@JsonProperty("championPointsUntilNextLevel")
	public Integer championPointsUntilNextLevel;
	@JsonProperty("markRequiredForNextLevel")
	public Integer markRequiredForNextLevel;
	@JsonProperty("tokensEarned")
	public Integer tokensEarned;
	@JsonProperty("championSeasonMilestone")
	public Integer championSeasonMilestone;
	@JsonProperty("milestoneGrades")
	public List<String> milestoneGrades;
	@JsonProperty("nextSeasonMilestone")
	public RiotNextSeasonMilestone nextSeasonMilestone;
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<>();

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
