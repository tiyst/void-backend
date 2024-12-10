package st.tiy.budgetopgg.service;

import com.riotgames.model.rotation.RiotChampionInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.domain.rotation.Rotation;
import st.tiy.budgetopgg.model.mapper.RotationMapper;

@Service
public class RotationService {

	private static final String ROTATION_BASE_URL = "https://eun1.api.riotgames.com/lol/platform/v3/champion-rotations";


	private final RestTemplate restTemplate;
	private final RotationMapper rotationMapper;

	public RotationService(RestTemplate restTemplate,
	                       RotationMapper rotationMapper) {
		this.restTemplate = restTemplate;
		this.rotationMapper = rotationMapper;
	}

	public Rotation getCurrentWeekRotation() {
		RiotChampionInfo response = restTemplate.getForObject(ROTATION_BASE_URL, RiotChampionInfo.class);

		return rotationMapper.mapToRotation(response);
	}

}
