package st.tiy.voidapp.api;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RiotUrlConstants {

	public static final String ACCOUNT_BASE_URL = "https://{region}.api.riotgames.com/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}";
	public static final String SUMMONER_BASE_URL = "https://{server}.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/{puuid}";
	public static final String ROTATION_BASE_URL = "https://{server}.api.riotgames.com/lol/platform/v3/champion-rotations";
	public static final String RANK_BASE_URL = "https://{server}.api.riotgames.com/lol/league/v4/entries/by-summoner/{summonerId}";

	public static final String FETCH_MATCH_IDS_URL = "https://{region}.api.riotgames.com/lol/match/v5/matches/by-puuid/{puuid}/ids";
	public static final String FETCH_MATCH_URL = "https://{region}.api.riotgames.com/lol/match/v5/matches/{matchId}";

	public static final String FETCH_MASTERY_IDS_URL = "https://{server}.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/{puuid}";
	public static final String FETCH_MASTERY_BY_CHAMP_URL_SUFFIX = "https://{server}.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/{puuid}/by-champion/{championId}"; //suffix is champion ID

}
