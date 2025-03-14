package st.tiy.voidapp.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
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

	public Mono<List<Rank>> getRanksBySummonerId(Server server, String summonerId) {
		return apiClient.getRankEntries(server, summonerId)
		                .flatMap(entries -> {
			                List<Rank> list = Arrays.stream(entries)
			                                        .map(this.riotRankMapper::mapToRank)
			                                        .toList();
			                return Mono.just(list);
		                });
	}

}
