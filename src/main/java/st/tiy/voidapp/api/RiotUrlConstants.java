package st.tiy.voidapp.api;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RiotUrlConstants {

	public static final String ACCOUNT_BASE_URL = "https://%s.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/%s";
	public static final String SUMMONER_BASE_URL = "https://%s.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/%s";
	public static final String ROTATION_BASE_URL = "https://%s.api.riotgames.com/lol/platform/v3/champion-rotations";
	public static final String RANK_BASE_URL = "https://%s.api.riotgames.com/lol/league/v4/entries/by-summoner/%s";

	public static final String FETCH_MATCH_IDS_URL = "https://%s.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids";
	public static final String FETCH_MATCH_URL = "https://%s.api.riotgames.com/lol/match/v5/matches/%s";

	public static final String FETCH_MASTERY_IDS_URL = "https://%s.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/%s";
	public static final String FETCH_MASTERY_BY_CHAMP_URL_SUFFIX = "https://%s.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/%s/by-champion/%s"; //suffix is champion ID

}
