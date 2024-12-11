package st.tiy.budgetopgg.service;

import com.riotgames.model.mastery.RiotChampionMastery;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.domain.mastery.ChampionMastery;
import st.tiy.budgetopgg.model.mapper.ChampionMasteryMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ChampionMasteryService {

	private static final String FETCH_MASTERY_IDS_URL = "https://eun1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/%s";
	private static final String FETCH_MASTERY_BY_CHAMP_URL_SUFFIX = "/by-champion/%s"; //suffix is champion ID

	private final RestTemplate restTemplate;
	private final ChampionMasteryMapper championMasteryMapper;

	public ChampionMasteryService(RestTemplate restTemplate,
	                              ChampionMasteryMapper championMasteryMapper) {
		this.restTemplate = restTemplate;
		this.championMasteryMapper = championMasteryMapper;
	}

	public List<ChampionMastery> getMasteryByPuuidAndChampionId(String puuid, String championId) {
		String url = (FETCH_MASTERY_IDS_URL + FETCH_MASTERY_BY_CHAMP_URL_SUFFIX).formatted(puuid, championId);

		return pullChampionMasteries(url);

	}

	public List<ChampionMastery> getMasteryByPuuid(String puuid) {
		String url = FETCH_MASTERY_IDS_URL.formatted(puuid);

		return pullChampionMasteries(url);
	}

	private List<ChampionMastery> pullChampionMasteries(String url) {
		RiotChampionMastery[] response = restTemplate.getForObject(url, RiotChampionMastery[].class);

		if (response == null) {
			return Collections.emptyList();
		}

		return Arrays.stream(response)
		             .map(championMasteryMapper::mapToChampionMastery)
		             .toList();
	}
}
