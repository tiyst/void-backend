package st.tiy.budgetopgg.service;

import org.springframework.stereotype.Service;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.repository.MatchRepository;

import java.util.Optional;

@Service
public class MatchService {

	public static final String MATCH_BASE_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/";

	private final MatchRepository matchRepository;

	public MatchService(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public Optional<Match> getMatchById(String matchId) {
		return matchRepository.findById(matchId);
	}

	public void getMatchesBySummoner(Summoner summoner) {
	}
}