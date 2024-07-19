package st.tiy.budgetopgg.service;

import org.springframework.stereotype.Service;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.repository.SummonerRepository;

@Service
public class SummonerService {

	public static final String BASE_URL = "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";

	private SummonerRepository repository;

	public SummonerService(SummonerRepository repository) {
		this.repository = repository;
	}

	public Summoner getSummoner(String gameName, String tagLine) {
		Summoner summoner = repository.getByGameNameAndTagLine(gameName, tagLine);

		if (summoner == null) {
			// TODO call Riot API with BASE_URL + GameName + TagLine;
			// TODO save to repo
			// TODO summoner = newly found
		}

		return summoner;
	}

}
