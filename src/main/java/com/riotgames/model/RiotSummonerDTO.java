package com.riotgames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RiotSummonerDTO {

	private String id;
	private String accountId;
	private String puuid;
	private int profileIconId;
	private long revisionDate;
	private int summonerLevel;

}
