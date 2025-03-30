package st.tiy.voidapp.trophy;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.repository.MatchRepository;
import st.tiy.voidapp.trophy.trophies.Trophy;
import st.tiy.voidapp.trophy.trophies.TrophyMostPings;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class TrophyCleanupRunner implements CommandLineRunner {

	private final TrophyRepository trophyRepository;
	private final MatchRepository matchRepository;

	// Inject the repository via constructor
	public TrophyCleanupRunner(TrophyRepository trophyRepository, MatchRepository matchRepository) {
		this.trophyRepository = trophyRepository;
		this.matchRepository = matchRepository;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		log.info("Starting trophy cleanup process...");

		List<Trophy> trophies = trophyRepository.findByTrophyType(TrophyMostPings.class);

		if (!trophies.isEmpty()) {
			log.info("Found {} trophies to delete.", trophies.size());
			List<Match> matches = trophies.stream().map(trophy -> {
				Optional<Match> match = matchRepository.findByMatchId(trophy.getBestMatch().getMatchId());
				match.ifPresent(value -> value.getTrophiedPuuids().remove(trophy.getSummoner().getPuuid()));
				return match.orElse(null);
			}).filter(Objects::nonNull).toList();
			matchRepository.saveAll(matches);
			trophyRepository.deleteAll(trophies);
			log.info("Deleted all trophies of type TrophyMostPings.");
		} else {
			log.info("No trophies found for type TrophyMostPings.");
		}
		log.info("Trophy cleanup process finished.");
	}
}