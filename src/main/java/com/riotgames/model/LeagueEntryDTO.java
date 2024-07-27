package com.riotgames.model;

import com.riotgames.model.league.MiniSeriesDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueEntryDTO {
	private String leagueId;
	private String queueType;
	private String tier;
	private String rank;
	private String summonerId;
	private int leaguePoints;
	private int wins;
	private int losses;
	private boolean veteran;
	private boolean inactive;
	private boolean freshBlood;
	private boolean hotStreak;
	private MiniSeriesDTO miniSeries;
}
