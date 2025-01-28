package st.tiy.budgetopgg.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static st.tiy.budgetopgg.api.Region.*;
import static st.tiy.budgetopgg.api.RiotUrlConstants.*;
import static st.tiy.budgetopgg.api.Server.*;

@Slf4j
@Service
public class RiotApiClient {
	private static final Map<Server, Region> serverToRegionTransform = Map.ofEntries(
			Map.entry(BR, AMERICAS),
			Map.entry(EUN1, EUROPE),
			Map.entry(EUW1, EUROPE),
			Map.entry(JP1, ASIA),
			Map.entry(KR, ASIA),
			Map.entry(LA1, AMERICAS),
			Map.entry(LA2, AMERICAS),
			Map.entry(ME1, ESPORTS),
			Map.entry(NA1, AMERICAS),
			Map.entry(OC1, ASIA),
			Map.entry(PH2, ASIA),
			Map.entry(RU, ASIA),
			Map.entry(SG2, ASIA),
			Map.entry(TH2, ASIA),
			Map.entry(TR1, EUROPE),
			Map.entry(TW2, ASIA),
			Map.entry(VN2, ASIA)
	);

	public String formatGetAccountUrl(Server server, String gameName, String tagLine) {
		return ACCOUNT_BASE_URL.formatted(serverToRegion(server), gameName, tagLine);
	}

	public String formatGetSummonertUrl(Server server, String puuid) {
		return SUMMONER_BASE_URL.formatted(server, puuid);
	}

	public String formatGetRankUrl(Server server, String summonerId) {
		return RANK_BASE_URL.formatted(server, summonerId);
	}

	public String formatGetMatchIdsUrl(Region region, String puuid) {
		return FETCH_MATCH_IDS_URL.formatted(region, puuid);
	}

	public String formatGetMatchUrl(Region region, String matchId) {
		return FETCH_MATCH_URL.formatted(region, matchId);
	}

	public String formatGetChampionMasteryUrl(Server server, String puuid) {
		return FETCH_MASTERY_IDS_URL.formatted(server, puuid);
	}

	public String formatGetChampionMasteryByChampionIdUrl(Server server, String puuid, String championId) {
		return FETCH_MASTERY_BY_CHAMP_URL_SUFFIX.formatted(server, puuid, championId);
	}

	public String formatGetRotationUrl(Server server) {
		return ROTATION_BASE_URL.formatted(server);

	}

	public Region serverToRegion(Server server) {
		return serverToRegionTransform.get(server);
	}

}
