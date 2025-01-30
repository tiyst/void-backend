package st.tiy.budgetopgg.model.dto;

import lombok.Getter;
import lombok.Setter;
import st.tiy.budgetopgg.model.dto.match.DtoMatch;

import java.util.List;

@Getter
@Setter
public class DtoSummoner {
	private String gameName;
	private String tagLine;
	private long lastUpdated; // Unix epoch of last time this summoner has been updated
	private String server;
	private int profileIcon;
	private long level;
	private List<DtoRank> rank;
	private List<DtoChampionMastery> masteries;
	private List<DtoMatch> matches;
}
