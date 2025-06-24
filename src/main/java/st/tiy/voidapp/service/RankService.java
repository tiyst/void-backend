package st.tiy.voidapp.service;

import com.riotgames.model.RiotLeagueEntryDTO;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.api.RiotApiClient;
import st.tiy.voidapp.api.Server;
import st.tiy.voidapp.model.domain.summoner.Rank;
import st.tiy.voidapp.model.mapper.RiotRankMapper;
import st.tiy.voidapp.repository.RankRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class RankService {

	private final RiotRankMapper riotRankMapper;
	private final RiotApiClient apiClient;
	private final RankRepository rankRepository;

	public RankService(RiotRankMapper riotRankMapper, RiotApiClient apiClient, RankRepository rankRepository) {
		this.riotRankMapper = riotRankMapper;
		this.apiClient = apiClient;
		this.rankRepository = rankRepository;
	}

	public List<Rank> getRanksBySummonerId(Server server, String summonerId) {
		return this.rankRepository.findBySummonerSummonerId(summonerId);
	}

	public List<Rank> pullRanksBySummonedPuuid(Server server, String puuid) {
		RiotLeagueEntryDTO[] url = apiClient.getRankEntries(server, puuid);

		return Arrays.stream(url)
		             .map(this.riotRankMapper::mapToRank)
		             .toList();
	}

}
