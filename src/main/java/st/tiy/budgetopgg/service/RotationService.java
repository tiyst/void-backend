package st.tiy.budgetopgg.service;

import com.riotgames.model.rotation.RiotChampionInfo;
import org.springframework.stereotype.Service;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.api.Server;
import st.tiy.budgetopgg.model.domain.rotation.Rotation;
import st.tiy.budgetopgg.model.mapper.RotationMapper;

@Service
public class RotationService {

	private final RotationMapper rotationMapper;
	private final RiotApiClient apiClient;

	public RotationService(RotationMapper rotationMapper, RiotApiClient apiClient) {
		this.rotationMapper = rotationMapper;
		this.apiClient = apiClient;
	}

	public Rotation getCurrentWeekRotation(Server server) {
		RiotChampionInfo rotation = apiClient.getRotation(server);

		return rotationMapper.mapToRotation(rotation);
	}

	public Rotation getCurrentWeekRotation() {
		return getCurrentWeekRotation(Server.EUW1);
	}

}
