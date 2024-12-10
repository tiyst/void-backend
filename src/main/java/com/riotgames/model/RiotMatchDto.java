package com.riotgames.model;

import com.riotgames.model.match.RiotInfo;
import com.riotgames.model.match.RiotMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RiotMatchDto {
	private RiotMetadata metadata;
	private RiotInfo info;
}
