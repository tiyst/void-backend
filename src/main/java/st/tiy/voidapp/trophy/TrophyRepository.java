package st.tiy.voidapp.trophy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import st.tiy.voidapp.model.domain.summoner.Summoner;
import st.tiy.voidapp.trophy.trophies.Trophy;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrophyRepository extends JpaRepository<Trophy, Long> {

	@Query("SELECT t FROM Trophy t WHERE t.summoner = :summoner AND TYPE(t) = :trophyType")
	Optional<Trophy> findBySummonerAndType(@Param("summoner") Summoner summoner,
	                                       @Param("trophyType") Class<? extends Trophy> trophyType);

	@Query("SELECT t FROM Trophy t WHERE TYPE(t) = :trophyType")
	List<Trophy> findByTrophyType(@Param("trophyType") Class<? extends Trophy> trophyType);
}
