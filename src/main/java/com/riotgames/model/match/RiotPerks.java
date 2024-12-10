package com.riotgames.model.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class RiotPerks {
	private RiotStatPerks statPerks;
	private ArrayList<RiotStyle> styles;
}
