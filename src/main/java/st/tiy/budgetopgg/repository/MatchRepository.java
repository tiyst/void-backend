package st.tiy.budgetopgg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.budgetopgg.model.domain.match.Match;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, String>{

	Optional<Match> findById(String matchId);
}
