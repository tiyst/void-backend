package com.riotgames.model.mastery;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

public class RiotNextSeasonMilestone {

	@JsonProperty("requireGradeCounts")
	public Map<String, Integer> requireGradeCounts;
	@JsonProperty("rewardMarks")
	public Integer rewardMarks;
	@JsonProperty("bonus")
	public Boolean bonus;
	@JsonProperty("totalGamesRequires")
	public Integer totalGamesRequires;
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
