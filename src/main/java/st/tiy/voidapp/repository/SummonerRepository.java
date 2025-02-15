package st.tiy.voidapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.voidapp.model.domain.summoner.Summoner;

import java.util.Optional;

@Repository
public interface SummonerRepository extends JpaRepository<Summoner, String> {

	Optional<Summoner> findSummonerByGameNameIgnoreCaseAndTagLineIgnoreCase(String gameName, String tagLine);

}
