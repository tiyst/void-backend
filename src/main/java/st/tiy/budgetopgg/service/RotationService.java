package st.tiy.budgetopgg.service;

import com.riotgames.model.rotation.RiotChampionInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.api.Server;
import st.tiy.budgetopgg.model.domain.rotation.Rotation;
import st.tiy.budgetopgg.model.mapper.RotationMapper;

@Service
public class RotationService {

	private final RestTemplate restTemplate;
	private final RotationMapper rotationMapper;
	private final RiotApiClient apiClient;

	public RotationService(RestTemplate restTemplate,
	                       RotationMapper rotationMapper,
	                       RiotApiClient apiClient) {
		this.restTemplate = restTemplate;
		this.rotationMapper = rotationMapper;
		this.apiClient = apiClient;
	}

	public Rotation getCurrentWeekRotation(Server server) {
		RiotChampionInfo response = restTemplate.getForObject(apiClient.formatGetRotationUrl(server), RiotChampionInfo.class);

		return rotationMapper.mapToRotation(response);
	}

	public Rotation getCurrentWeekRotation() {
		return getCurrentWeekRotation(Server.EUW1);
	}

}
