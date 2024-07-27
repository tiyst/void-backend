package com.riotgames.model.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Info {
	private String endOfGameResult;
	private long gameCreation;
	private int gameDuration;
	private long gameEndTimestamp;
	private long gameId;
	private String gameMode;
	private String gameName;
	private long gameStartTimestamp;
	private String gameType;
	private String gameVersion;
	private int mapId;
	private ArrayList<ParticipantDto> participantDtos;
	private String platformId;
	private int queueId;
	private ArrayList<TeamDto> teamDtos;
	private String tournamentCode;
}
