package st.tiy.voidapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.repository.MatchRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@ConditionalOnProperty(value = "voidapp.cleanup.enabled", havingValue = "true")
public class OldMatchCleanupService {

	private final int maxDaysAge;

	private final MatchRepository matchRepository;

	public OldMatchCleanupService(@Value("${voidapp.cleanup.cutoff-days:30}") int maxDaysAge,
	                              MatchRepository repository) {
		this.maxDaysAge = maxDaysAge;
		this.matchRepository = repository;
		log.info("Initialized OldMatchCleanupService with days {}", maxDaysAge);
	}

	@Transactional
	@Scheduled(cron = "${voidapp.cleanup.cron}")
	public void cleanUpOldMatches() {
		log.info("Starting old matches cleanup");
		Instant cutoffInstant = Instant.now().minus(maxDaysAge, ChronoUnit.DAYS);
		long cutoffTimestamp = cutoffInstant.getEpochSecond() * 1000;

		List<Match> oldMatches = matchRepository.findAllByGameStartTimestampBefore(cutoffTimestamp)
		                                        .stream()
		                                        .filter(match -> match.getTrophiedPuuids().isEmpty())
		                                        .toList();

		if (!oldMatches.isEmpty()) {
			matchRepository.deleteAll(oldMatches);
			log.info("Cleaned up old {} matches", oldMatches.size());
		} else {
			log.info("No old matches to delete.");
		}
	}
}
