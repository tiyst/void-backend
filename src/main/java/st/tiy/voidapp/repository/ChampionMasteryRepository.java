package st.tiy.voidapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import st.tiy.voidapp.model.domain.mastery.ChampionMastery;

import java.util.List;

public interface ChampionMasteryRepository extends JpaRepository<ChampionMastery, Long> {

	List<ChampionMastery> findAllBySummonerPuuid(String puuid);

}
