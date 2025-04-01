package st.tiy.voidapp.queue.task.trophyroom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.model.domain.summoner.Summoner;
import st.tiy.voidapp.queue.task.VoidTask;
import st.tiy.voidapp.queue.task.VoidTaskExecutor;
import st.tiy.voidapp.repository.SummonerRepository;
import st.tiy.voidapp.trophy.TrophyRoomService;

import java.util.Optional;

@Slf4j
@Service
@ConditionalOnProperty(value = "voidapp.processingQueue.trophyRoomProcessing", havingValue = "true")
public class TrophyRoomTaskExecutor extends VoidTaskExecutor<TrophyRoomTaskParameters> {

	private final TrophyRoomService trophyRoomService;
	private final SummonerRepository summonerRepository;

	public TrophyRoomTaskExecutor(TrophyRoomService trophyRoomService,
	                              SummonerRepository summonerRepository) {
		this.trophyRoomService = trophyRoomService;
		this.summonerRepository = summonerRepository;
	}

	@Override
	public void processTask(VoidTask<TrophyRoomTaskParameters> params) {
		TrophyRoomTaskParameters parameters = params.getParameters();
		String puuid = parameters.getPuuid();
		Optional<Summoner> summonerByPuuid = summonerRepository.findSummonerByPuuid(puuid);

		if (summonerByPuuid.isEmpty()) {
			log.warn("No such summoner found for puuid: {}", puuid);
			return;
		}
		trophyRoomService.processMatchesByMatchIds(parameters.getMatchIds(), summonerByPuuid.get());
	}

	@Override
	public Class<TrophyRoomTaskParameters> getSupportedParametersType() {
		return TrophyRoomTaskParameters.class;
	}

}
