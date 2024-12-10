package com.riotgames.model.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class RiotTeamDto {
	private ArrayList<RiotBanDto> bans;
	private RiotObjectives objectives;
	private int teamId;
	private boolean win;
}
