package com.riotgames.model;

import com.riotgames.model.match.Info;
import com.riotgames.model.match.Metadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchDto {
	private Metadata metadata;
	private Info info;
}
