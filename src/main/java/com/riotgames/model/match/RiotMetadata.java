package com.riotgames.model.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class RiotMetadata {
	private String dataVersion;
	private String matchId;
	private ArrayList<String> participants;
}
