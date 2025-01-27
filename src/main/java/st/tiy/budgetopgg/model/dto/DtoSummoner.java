package st.tiy.budgetopgg.model.dto;

import st.tiy.budgetopgg.model.dto.match.DtoMatch;

import java.util.List;

public record DtoSummoner(
	String gameName,
	String tagLine,
	long lastUpdated, // Unix epoch of last time this summoner has been updated
	String server,
	int profileIcon,
	long level,
	List<DtoRank> rank,
	List<DtoChampionMastery> masteries,
	List<DtoMatch> matches
) {
}
