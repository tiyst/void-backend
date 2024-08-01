package st.tiy.budgetopgg.service;

import com.riotgames.model.LeagueEntryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.mapper.RankMapper;
import st.tiy.budgetopgg.repository.RankRepository;
import st.tiy.budgetopgg.repository.SummonerRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
public class RankService {
	public static final String RANK_BASE_URL = "https://eun1.api.riotgames.com/lol/league/v4/entries/by-summoner/%s?api_key=%s";

	private final String API_KEY;
	private final RestTemplate restTemplate;
	// TODO RankMapper - mapper into domain Rank entity
	private final RankMapper rankMapper;
	private final SummonerRepository summonerRepository;
	private final RankRepository rankRepository;

	public RankService(@Value("${api.key}") String apiKey,
					   RestTemplate restTemplate, RankMapper rankMapper, SummonerRepository summonerRepository, RankRepository rankRepository) {
		this.restTemplate = restTemplate;
		this.API_KEY = apiKey;
		this.rankMapper = rankMapper;
		this.summonerRepository = summonerRepository;
		this.rankRepository = rankRepository;
	}

	public void getRanksBySummonerId(String summonerId) {
		String url = String.format(RANK_BASE_URL, summonerId, API_KEY);
		LeagueEntryDTO[] response = restTemplate.getForObject(url, LeagueEntryDTO[].class);

//		System.out.println("API Response: " + Arrays.toString(response));
//		for (LeagueEntryDTO entry : response) {
//			System.out.println("LeagueEntryDTO: " + entry);
//			System.out.println("SummonerId: " + entry.getSummonerId());
//			System.out.println("QueueType: " + entry.getQueueType());
//			System.out.println("Tier: " + entry.getTier());
//			System.out.println("Rank: " + entry.getRank());
//			System.out.println("LeaguePoints: " + entry.getLeaguePoints());
//		}
//
//		Optional<Summoner> summonerOptional = summonerRepository.findBySummonerId(summonerId);
//		if (summonerOptional.isPresent()) {
//			Summoner summoner = summonerOptional.get();
//			System.out.println("Ranks cleared");
//			summoner.clearRanks();
//
//			for (LeagueEntryDTO entry : response) {
//				Rank rank = rankMapper.mapWithLogging(entry);
//				System.out.println("Mapped Rank: " + rank); // Log the mapped Rank
//				summoner.addRank(rank);
//				rankRepository.save(rank);
//				System.out.println("Saved Rank: " + rank);
//			}
//			summonerRepository.save(summoner);
//			System.out.println("Saved Summoner with Ranks: " + summoner);
//		}

		Optional<Summoner> summonerOptional = summonerRepository.findBySummonerId(summonerId);
		if (summonerOptional != null) {
			Summoner summoner = summonerOptional.get();
			summoner.clearRanks();

			for (LeagueEntryDTO entry : response) {
				Rank rank = rankMapper.mapWithLogging(entry);
				summoner.addRank(rank);
				rankRepository.save(rank);
			}
			summonerRepository.save(summoner);
		}

	}

}
