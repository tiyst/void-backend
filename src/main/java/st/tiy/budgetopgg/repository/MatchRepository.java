package st.tiy.budgetopgg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import st.tiy.budgetopgg.model.domain.match.Match;

public interface MatchRepository extends JpaRepository<Match, String> {

//	List<Match> findAllByPlayersPuuidsContaining(String puuid);

}
