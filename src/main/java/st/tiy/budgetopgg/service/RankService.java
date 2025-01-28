package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotLeagueEntryDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.api.Server;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.model.mapper.RankMapper;
import st.tiy.budgetopgg.repository.RankRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RankService {

	private final RestTemplate restTemplate;
	private final RankMapper rankMapper;
	private final RiotApiClient apiClient;

	private final RankRepository rankRepository;

	public RankService(RestTemplate restTemplate,
	                   RankMapper rankMapper,
	                   RankRepository rankRepository,
	                   RiotApiClient apiClient) {
		this.restTemplate = restTemplate;
		this.rankMapper = rankMapper;
		this.rankRepository = rankRepository;
		this.apiClient = apiClient;
	}

	public List<Rank> getRanksBySummonerId(Server server, String summonerId) {
		RiotLeagueEntryDTO[] response = restTemplate.getForObject(apiClient.formatGetRankUrl(server, summonerId), RiotLeagueEntryDTO[].class);

		if (response == null) {
			return Collections.emptyList();
		}

		// TODO save to repository
		return Arrays.stream(response)
		             .map(this.rankMapper::mapToRank)
		             .toList();
	}

}
