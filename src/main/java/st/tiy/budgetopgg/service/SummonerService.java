package st.tiy.budgetopgg.service;

import org.springframework.stereotype.Service;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.repository.SummonerRepository;

import java.util.Optional;

@Service
public class SummonerService {

	public static final String BASE_URL = "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";

	private SummonerRepository repository;

	public SummonerService(SummonerRepository repository) {
		this.repository = repository;
	}

	public Optional<Summoner> getSummoner(String gameName, String tagLine) {
		Optional<Summoner> summonerOptional = repository.findByGameNameAndTagLine(gameName, tagLine);

		if (summonerOptional == null) {
			// TODO call Riot API with BASE_URL + GameName + TagLine;
			// TODO save to repo
			// TODO summoner = newly found
		}

		return summonerOptional;
	}

}
