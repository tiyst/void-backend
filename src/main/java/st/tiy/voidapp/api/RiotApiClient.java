package st.tiy.voidapp.api;

import com.riotgames.model.RiotAccountDto;
import com.riotgames.model.RiotLeagueEntryDTO;
import com.riotgames.model.RiotMatchDto;
import com.riotgames.model.RiotSummonerDTO;
import com.riotgames.model.mastery.RiotChampionMastery;
import com.riotgames.model.rotation.RiotChampionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

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

	private final WebClient webClient;

	public RiotApiClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public RiotAccountDto getAccount(Server server, String gameName, String tagLine) {
		Region region = serverToRegion(server);

		return webClient.get()
		                .uri(ACCOUNT_BASE_URL, region, gameName, tagLine)
		                .retrieve()
		                .bodyToMono(RiotAccountDto.class)
		                .block();
	}

	public RiotSummonerDTO getSummoner(Server server, String puuid) {
		return webClient.get()
		                .uri(SUMMONER_BASE_URL, server, puuid)
		                .retrieve()
		                .bodyToMono(RiotSummonerDTO.class)
		                .block();
	}

	public Mono<RiotLeagueEntryDTO[]> getRankEntries(Server server, String summonerId) {
		return webClient.get()
		                .uri(RANK_BASE_URL, server, summonerId)
		                .retrieve()
		                .bodyToMono(RiotLeagueEntryDTO[].class)
		                .defaultIfEmpty(new RiotLeagueEntryDTO[0]);
	}

	public Mono<String[]> getMatchIds(Region region, String puuid, LocalDateTime lastMatchTimestamp) {
		//startTime long 	Epoch timestamp in seconds. The matchlist started storing timestamps on June 16th, 2021. Any matches played before June 16th, 2021 won't be included in the results if the startTime filter is set.
		//endTime 	long 	Epoch timestamp in seconds.
		//queue     int 	Filter the list of match ids by a specific queue id. This filter is mutually inclusive of the type filter meaning any match ids returned must match both the queue and type filters.
		//type      string 	Filter the list of match ids by the type of match. This filter is mutually inclusive of the queue filter meaning any match ids returned must match both the queue and type filters.
		//start	    int     Defaults to 0. Start index.
		//count     int     Defaults to 20. Valid values: 0 to 100. Number of match ids to return.
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(FETCH_MATCH_IDS_URL)
				.queryParamIfPresent("startTime", Optional.of(lastMatchTimestamp.toEpochSecond(ZoneOffset.UTC)))
				.queryParam("count", "10");

		return webClient.get()
		                .uri(builder.buildAndExpand(region, puuid).toUriString())
		                .retrieve()
		                .bodyToMono(String[].class)
		                .defaultIfEmpty(new String[0]);
	}

	public Mono<RiotMatchDto> getMatch(Region region, String matchId) {
		return webClient.get()
		                .uri(FETCH_MATCH_URL, region, matchId)
		                .retrieve()
		                .bodyToMono(RiotMatchDto.class);
	}

	public Mono<RiotChampionMastery[]> getChampionMastery(Server server, String puuid) {
		return webClient.get()
		                .uri(FETCH_MASTERY_IDS_URL, server, puuid)
		                .retrieve()
		                .bodyToMono(RiotChampionMastery[].class)
		                .defaultIfEmpty(new RiotChampionMastery[0]);
	}

	public Mono<RiotChampionMastery> getChampionMasteryByChampionId(Server server, String puuid, String championId) {
		return webClient.get()
				.uri(FETCH_MASTERY_BY_CHAMP_URL_SUFFIX, server, puuid, championId)
				.retrieve()
				.bodyToMono(RiotChampionMastery.class);
	}

	public Mono<RiotChampionInfo> getRotation(Server server) {
		return webClient.get()
				.uri(ROTATION_BASE_URL, server)
				.retrieve()
				.bodyToMono(RiotChampionInfo.class);
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
