package st.tiy.budgetopgg.model.domain.summoner;

public class Summoner {

	private String puuid;
	private String accountId;
	private String id;
	private long lastUpdated; // Unix epoch of last time this summoner has been updated

	private String gameName;
	private String tagLine;
	private String server;
	private long level;

	private Rank rank;

}
