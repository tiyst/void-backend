package st.tiy.budgetopgg.service;

import com.riotgames.model.rotation.ChampionInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.domain.rotation.Rotation;
import st.tiy.budgetopgg.model.mapper.RotationMapper;

@Service
public class RotationService {

	private static final String ROTATION_BASE_URL = "https://eun1.api.riotgames.com/lol/platform/v3/champion-rotations";

	private final String API_KEY;

	private final RestTemplate restTemplate;
	private final RotationMapper rotationMapper;

	public RotationService(@Value("${api.key}") String apiKey,
	                       RestTemplate restTemplate,
	                       RotationMapper rotationMapper) {
		this.restTemplate = restTemplate;
		this.rotationMapper = rotationMapper;
		this.API_KEY = apiKey;
	}

	public Rotation getCurrentWeekRotation() {
		ChampionInfo response = restTemplate.getForObject(ROTATION_BASE_URL + "?api_key=" + this.API_KEY, ChampionInfo.class);

		return rotationMapper.mapToRotation(response);
	}

}