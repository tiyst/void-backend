package com.riotgames.model.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class RiotStyle {
	private String description;
	private ArrayList<RiotSelection> selections;
	private int style;
}
