package st.tiy.budgetopgg.service;

import com.riotgames.model.mastery.RiotChampionMastery;
import org.springframework.stereotype.Service;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.api.Server;
import st.tiy.budgetopgg.model.domain.mastery.ChampionMastery;
import st.tiy.budgetopgg.model.mapper.ChampionMasteryMapper;

import java.util.Arrays;
import java.util.List;

@Service
public class ChampionMasteryService {

	private final ChampionMasteryMapper championMasteryMapper;
	private final RiotApiClient apiClient;

	public ChampionMasteryService(ChampionMasteryMapper championMasteryMapper,
	                              RiotApiClient apiClient) {
		this.championMasteryMapper = championMasteryMapper;
		this.apiClient = apiClient;
	}

	public ChampionMastery getMasteryByPuuidAndChampionId(Server server, String puuid, String championId) {
		RiotChampionMastery mastery = apiClient.getChampionMasteryByChampionId(server, puuid, championId);

		return championMasteryMapper.mapToChampionMastery(mastery);
	}

	public List<ChampionMastery> getMasteryByPuuid(Server server, String puuid) {
		RiotChampionMastery[] championMastery = apiClient.getChampionMastery(server, puuid);

		return Arrays.stream(championMastery)
		             .map(championMasteryMapper::mapToChampionMastery)
		             .toList();
	}
}
