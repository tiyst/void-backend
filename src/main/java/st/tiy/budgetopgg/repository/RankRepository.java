package st.tiy.budgetopgg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.budgetopgg.model.domain.summoner.Rank;

//TODO rank repo

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
}
