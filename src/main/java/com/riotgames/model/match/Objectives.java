package com.riotgames.model.match;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("java:S1104")
public class Objectives {
	public ObjectiveDto baron;
	public ObjectiveDto champion;
	public ObjectiveDto dragon;
	public ObjectiveDto horde;
	public ObjectiveDto inhibitor;
	public ObjectiveDto riftHerald;
	public ObjectiveDto tower;
}
