package st.tiy.budgetopgg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;

@Repository
public interface SummonerRepository extends JpaRepository<Summoner, Long> {

	Summoner findByGameNameAndTagLine(String gameName, String tagLine);

}
