package com.riotgames.model.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class TeamDto {
	private ArrayList<BanDto> bans;
	private Objectives objectives;
	private int teamId;
	private boolean win;
}
