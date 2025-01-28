package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotLeagueEntryDTO;
import org.springframework.stereotype.Service;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.api.Server;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.model.mapper.RankMapper;

import java.util.Arrays;
import java.util.List;

@Service
public class RankService {

	private final RankMapper rankMapper;
	private final RiotApiClient apiClient;

	public RankService(RankMapper rankMapper, RiotApiClient apiClient) {
		this.rankMapper = rankMapper;
		this.apiClient = apiClient;
	}

	public List<Rank> getRanksBySummonerId(Server server, String summonerId) {
		RiotLeagueEntryDTO[] url = apiClient.getRankEntries(server, summonerId);

		return Arrays.stream(url)
		             .map(this.rankMapper::mapToRank)
		             .toList();
	}

}
