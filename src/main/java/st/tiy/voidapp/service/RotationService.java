package st.tiy.voidapp.service;

import com.riotgames.model.rotation.RiotChampionInfo;
import org.springframework.stereotype.Service;
import st.tiy.voidapp.api.RiotApiClient;
import st.tiy.voidapp.api.Server;
import st.tiy.voidapp.model.domain.rotation.Rotation;
import st.tiy.voidapp.model.mapper.RiotRotationMapper;

@Service
public class RotationService {

	private final RiotRotationMapper riotRotationMapper;
	private final RiotApiClient apiClient;

	public RotationService(RiotRotationMapper riotRotationMapper, RiotApiClient apiClient) {
		this.riotRotationMapper = riotRotationMapper;
		this.apiClient = apiClient;
	}

	public Rotation getCurrentWeekRotation(Server server) {
		RiotChampionInfo rotation = apiClient.getRotation(server);

		return riotRotationMapper.mapToRotation(rotation);
	}

	public Rotation getCurrentWeekRotation() {
		return getCurrentWeekRotation(Server.EUW1);
	}

}
