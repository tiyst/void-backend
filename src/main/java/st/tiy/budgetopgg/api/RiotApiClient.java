package st.tiy.budgetopgg.api;

import com.riotgames.model.RiotAccountDto;
import com.riotgames.model.RiotLeagueEntryDTO;
import com.riotgames.model.RiotMatchDto;
import com.riotgames.model.RiotSummonerDTO;
import com.riotgames.model.mastery.RiotChampionMastery;
import com.riotgames.model.rotation.RiotChampionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.ArrayUtils;

import java.util.Map;

import static st.tiy.budgetopgg.api.Region.AMERICAS;
import static st.tiy.budgetopgg.api.Region.ASIA;
import static st.tiy.budgetopgg.api.Region.ESPORTS;
import static st.tiy.budgetopgg.api.Region.EUROPE;
import static st.tiy.budgetopgg.api.RiotUrlConstants.*;
import static st.tiy.budgetopgg.api.Server.*;

@Slf4j
@Service
public class RiotApiClient {

	private final RestTemplate restTemplate;

	public RiotApiClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public RiotAccountDto getAccount(Server server, String gameName, String tagLine) {
		String accountUrl = ACCOUNT_BASE_URL.formatted(serverToRegion(server), gameName, tagLine);

		return restTemplate.getForObject(accountUrl, RiotAccountDto.class);
	}

	public RiotSummonerDTO getSummoner(Server server, String puuid) {
		String summonerUrl = SUMMONER_BASE_URL.formatted(server, puuid);

		return restTemplate.getForObject(summonerUrl, RiotSummonerDTO.class);
	}

	public RiotLeagueEntryDTO[] getRankEntries(Server server, String summonerId) {
		String rankUrl = RANK_BASE_URL.formatted(server, summonerId);

		RiotLeagueEntryDTO[] response = restTemplate.getForObject(rankUrl, RiotLeagueEntryDTO[].class);

		if (response == null) {
			return new RiotLeagueEntryDTO[0];
		}

		return response;
	}

	public String[] getMatchIds(Region region, String puuid) {
		String url = FETCH_MATCH_IDS_URL.formatted(region, puuid);
		String[] matchIds = this.restTemplate.getForObject(url, String[].class);

		if (ArrayUtils.isEmpty(matchIds)) {
			return new String[0];
		}

		return matchIds;
	}

	public RiotMatchDto getMatch(Region region, String matchId) {
		String url = FETCH_MATCH_URL.formatted(region, matchId);

		return this.restTemplate.getForObject(url, RiotMatchDto.class);
	}

	public RiotChampionMastery[] getChampionMastery(Server server, String puuid) {
		String url = FETCH_MASTERY_IDS_URL.formatted(server, puuid);
		RiotChampionMastery[] response = restTemplate.getForObject(url, RiotChampionMastery[].class);

		if (ArrayUtils.isEmpty(response)) {
			return new RiotChampionMastery[0];
		}

		return response;
	}

	public RiotChampionMastery getChampionMasteryByChampionId(Server server, String puuid, String championId) {
		String url = FETCH_MASTERY_BY_CHAMP_URL_SUFFIX.formatted(server, puuid, championId);

		return restTemplate.getForObject(url, RiotChampionMastery.class);
	}

	public RiotChampionInfo getRotation(Server server) {
		String url = ROTATION_BASE_URL.formatted(server);

		return restTemplate.getForObject(url, RiotChampionInfo.class);
	}

	public Region serverToRegion(Server server) {
		return serverToRegionTransform.get(server);
	}

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
}
