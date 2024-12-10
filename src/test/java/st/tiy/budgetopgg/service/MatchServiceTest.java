package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotMatchDto;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import st.tiy.budgetopgg.model.mapper.MatchDtoMatchMapper;
import st.tiy.budgetopgg.repository.MatchRepository;
import st.tiy.budgetopgg.utils.FileLoaderUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MatchServiceTest {

	private static MatchService matchService;
	private static RestTemplate restTemplate;
	private static RiotMatchDto exampleMatch;
	private static MatchRepository matchRepository;
	private static MatchDtoMatchMapper mapper;


	@BeforeAll
	static void setUpBeforeClass() throws IOException {
		restTemplate = mock(RestTemplate.class);
		mapper = mock(MatchDtoMatchMapper.class);
		matchRepository = mock(MatchRepository.class);
		matchService = new MatchService(restTemplate, matchRepository, mapper);
		exampleMatch = FileLoaderUtil.loadFile("example/exampleMatch.json", RiotMatchDto.class);
	}

	@Test
	void exampleMatchLoadsTest() {
		assertThat(exampleMatch).isNotNull();
	}


}