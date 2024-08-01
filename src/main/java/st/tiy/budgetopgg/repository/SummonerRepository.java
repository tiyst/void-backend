package st.tiy.budgetopgg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;

import java.util.Optional;

@Repository
public interface SummonerRepository extends JpaRepository<Summoner, String> {

	Optional<Summoner> findByGameNameAndTagLine(String gameName, String tagLine);

	Optional<Summoner> findBySummonerId(String summonerId);
}
