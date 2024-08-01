package st.tiy.budgetopgg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.budgetopgg.model.domain.summoner.RankSeries;

@Repository
public interface RankSeriesRepository extends JpaRepository<RankSeries, Long> {

	RankSeries findByRankId(Long rankId);

}
