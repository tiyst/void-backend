package st.tiy.voidapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import st.tiy.voidapp.model.domain.mastery.ChampionMastery;

import java.util.List;

// TODO add option to how many to retrieve, default 4
public interface ChampionMasteryRepository extends JpaRepository<ChampionMastery, Long> {

	List<ChampionMastery> findAllBySummonerPuuid(String puuid);

}
