package st.tiy.voidapp.api;

import com.riotgames.model.RiotAccountDto;
import com.riotgames.model.RiotLeagueEntryDTO;
import com.riotgames.model.RiotMatchDto;
import com.riotgames.model.RiotSummonerDTO;
import com.riotgames.model.mastery.RiotChampionMastery;
import com.riotgames.model.rotation.RiotChampionInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;

import static st.tiy.voidapp.api.Region.AMERICAS;
import static st.tiy.voidapp.api.Region.ASIA;
import static st.tiy.voidapp.api.Region.ESPORTS;
import static st.tiy.voidapp.api.Region.EUROPE;
import static st.tiy.voidapp.api.RiotUrlConstants.ACCOUNT_BASE_URL;
import static st.tiy.voidapp.api.RiotUrlConstants.FETCH_MASTERY_BY_CHAMP_URL_SUFFIX;
import static st.tiy.voidapp.api.RiotUrlConstants.FETCH_MASTERY_IDS_URL;
import static st.tiy.voidapp.api.RiotUrlConstants.FETCH_MATCH_IDS_URL;
import static st.tiy.voidapp.api.RiotUrlConstants.FETCH_MATCH_URL;
import static st.tiy.voidapp.api.RiotUrlConstants.RANK_BASE_URL;
import static st.tiy.voidapp.api.RiotUrlConstants.ROTATION_BASE_URL;
import static st.tiy.voidapp.api.RiotUrlConstants.SUMMONER_BASE_URL;
import static st.tiy.voidapp.api.Server.*;

@Slf4j
@Service
public class RiotApiClient {
	public static final LocalDateTime MIN_LOCALDATETIME = LocalDateTime.of(2020, 1, 1, 0, 0);

	private final RestTemplate restTemplate;

	public RiotApiClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public RiotAccountDto getAccount(Server server, String gameName, String tagLine) {
		String accountUrl = ACCOUNT_BASE_URL.formatted(serverToRegion(server), gameName, tagLine);

		log.info("accountUrl: {}", accountUrl);
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
		return getMatchIds(region, puuid, LocalDateTime.now());
	}

	public String[] getMatchIds(Region region, String puuid, LocalDateTime endTimestamp) {
		//startTime long 	Epoch timestamp in seconds. The matchlist started storing timestamps on June 16th, 2021. Any matches played before June 16th, 2021 won't be included in the results if the startTime filter is set.
		//endTime 	long 	Epoch timestamp in seconds.
		//queue     int 	Filter the list of match ids by a specific queue id. This filter is mutually inclusive of the type filter meaning any match ids returned must match both the queue and type filters.
		//type      string 	Filter the list of match ids by the type of match. This filter is mutually inclusive of the queue filter meaning any match ids returned must match both the queue and type filters.
		//start	    int     Defaults to 0. Start index.
		//count     int     Defaults to 20. Valid values: 0 to 100. Number of match ids to return.
		String url = FETCH_MATCH_IDS_URL.formatted(region, puuid);

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(url)
				.queryParamIfPresent("startTime", Optional.of(MIN_LOCALDATETIME.toEpochSecond(ZoneOffset.UTC)))
				.queryParamIfPresent("endTime", Optional.of(endTimestamp.toEpochSecond(ZoneOffset.UTC)))
				.queryParam("count", "10");

		String[] matchIds = this.restTemplate.getForObject(builder.toUriString(), String[].class);
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
