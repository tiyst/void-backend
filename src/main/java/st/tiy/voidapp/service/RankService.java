package st.tiy.voidapp.service;

import com.riotgames.model.RiotLeagueEntryDTO;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.api.RiotApiClient;
import st.tiy.voidapp.api.Server;
import st.tiy.voidapp.model.domain.summoner.Rank;
import st.tiy.voidapp.model.mapper.RiotRankMapper;

import java.util.Arrays;
import java.util.List;

@Service
public class RankService {

	private final RiotRankMapper riotRankMapper;
	private final RiotApiClient apiClient;

	public RankService(RiotRankMapper riotRankMapper, RiotApiClient apiClient) {
		this.riotRankMapper = riotRankMapper;
		this.apiClient = apiClient;
	}

	public List<Rank> getRanksBySummonerId(Server server, String summonerId) {
		RiotLeagueEntryDTO[] url = apiClient.getRankEntries(server, summonerId);

		return Arrays.stream(url)
		             .map(this.riotRankMapper::mapToRank)
		             .toList();
	}

}
