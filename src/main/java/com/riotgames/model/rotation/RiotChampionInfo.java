package com.riotgames.model.rotation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RiotChampionInfo {

	private int maxNewPlayerLevel;
	private List<Integer> freeChampionIdsForNewPlayers;
	private List<Integer> freeChampionIds;

}
