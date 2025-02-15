package st.tiy.voidapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.voidapp.model.domain.summoner.RankSeries;

@Repository
public interface RankSeriesRepository extends JpaRepository<RankSeries, Long> {

	RankSeries findByRankId(Long rankId);

}
