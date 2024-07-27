package com.riotgames.model.match;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Objectives {
	private Baron baron;
	private Champion champion;
	private Dragon dragon;
	private Horde horde;
	private Inhibitor inhibitor;
	private RiftHerald riftHerald;
	private Tower tower;
}
