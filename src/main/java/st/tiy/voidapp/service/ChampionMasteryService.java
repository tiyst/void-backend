package st.tiy.voidapp.service;

import com.riotgames.model.mastery.RiotChampionMastery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.api.RiotApiClient;
import st.tiy.voidapp.api.Server;
import st.tiy.voidapp.model.domain.mastery.ChampionMastery;
import st.tiy.voidapp.model.mapper.RiotChampionMasteryMapper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ChampionMasteryService {

	private final RiotChampionMasteryMapper riotChampionMasteryMapper;
	private final RiotApiClient apiClient;
	private final int masteryCountToReturn;

	public ChampionMasteryService(RiotChampionMasteryMapper riotChampionMasteryMapper,
	                              RiotApiClient apiClient,
	                              @Value("${voidapp.mastery.count:4}") int masteryCountToReturn) {
		this.riotChampionMasteryMapper = riotChampionMasteryMapper;
		this.apiClient = apiClient;
		this.masteryCountToReturn = masteryCountToReturn;
	}

	public ChampionMastery getMasteryByPuuidAndChampionId(Server server, String puuid, String championId) {
		RiotChampionMastery mastery = apiClient.getChampionMasteryByChampionId(server, puuid, championId);

		return riotChampionMasteryMapper.mapToChampionMastery(mastery);
	}

	public List<ChampionMastery> getMasteryByPuuid(Server server, String puuid) {
		RiotChampionMastery[] championMastery = apiClient.getChampionMastery(server, puuid);

		return Arrays.stream(championMastery)
		             .limit(masteryCountToReturn)
		             .map(riotChampionMasteryMapper::mapToChampionMastery)
		             .sorted(Comparator.comparingInt(ChampionMastery::getChampionPoints).reversed())
		             .toList();
	}
}
