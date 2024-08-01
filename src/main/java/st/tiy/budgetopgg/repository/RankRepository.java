package st.tiy.budgetopgg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import st.tiy.budgetopgg.model.domain.summoner.Rank;

import java.util.List;

public interface RankRepository extends JpaRepository<Rank, Long> {

	List<Rank> findBySummonerPuuid(String puuid);

	List<Rank> findBySummonerSummonerId(String summonerId);

}
