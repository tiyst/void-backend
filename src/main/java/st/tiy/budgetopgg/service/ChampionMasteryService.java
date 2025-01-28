package st.tiy.budgetopgg.service;

import com.riotgames.model.mastery.RiotChampionMastery;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.api.Server;
import st.tiy.budgetopgg.model.domain.mastery.ChampionMastery;
import st.tiy.budgetopgg.model.mapper.ChampionMasteryMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ChampionMasteryService {

	private final RestTemplate restTemplate;
	private final ChampionMasteryMapper championMasteryMapper;
	private final RiotApiClient apiClient;

	public ChampionMasteryService(RestTemplate restTemplate,
	                              ChampionMasteryMapper championMasteryMapper,
	                              RiotApiClient apiClient) {
		this.restTemplate = restTemplate;
		this.championMasteryMapper = championMasteryMapper;
		this.apiClient = apiClient;
	}

	public ChampionMastery getMasteryByPuuidAndChampionId(Server server, String puuid, String championId) {
		return pullChampionMastery(
				apiClient.formatGetChampionMasteryByChampionIdUrl(server, puuid, championId));

	}

	public List<ChampionMastery> getMasteryByPuuid(Server server, String puuid) {
		return pullChampionMasteries(apiClient.formatGetChampionMasteryUrl(server, puuid));
	}

	private ChampionMastery pullChampionMastery(String url) {
		RiotChampionMastery mastery = restTemplate.getForObject(url, RiotChampionMastery.class);

		return championMasteryMapper.mapToChampionMastery(mastery);
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
