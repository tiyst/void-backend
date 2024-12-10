package com.riotgames.model.match;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("java:S1104")
public class RiotObjectives {
	public RiotObjectiveDto baron;
	public RiotObjectiveDto champion;
	public RiotObjectiveDto dragon;
	public RiotObjectiveDto horde;
	public RiotObjectiveDto inhibitor;
	public RiotObjectiveDto riftHerald;
	public RiotObjectiveDto tower;
}
