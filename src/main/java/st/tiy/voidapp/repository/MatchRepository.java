package st.tiy.voidapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import st.tiy.voidapp.model.domain.match.Match;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, String> {

	List<Match> findAllByParticipantIdsContaining(String puuid);

	List<Match> findAllByGameStartTimestampBefore(long gameStartTimestampBefore);

}
