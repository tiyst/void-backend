package st.tiy.voidapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import st.tiy.voidapp.model.domain.match.Match;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, String> {

	Page<Match> findByParticipantIdsContaining(String puuid, Pageable pageable);

	List<Match> findAllByGameStartTimestampBefore(long gameStartTimestampBefore);

	List<Match> findAllByMatchIdIsIn(Collection<String> matchIds);

	Optional<Match> findByMatchId(String matchId);
}
