package st.tiy.budgetopgg.service;

import com.riotgames.model.RiotMatchDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import st.tiy.budgetopgg.api.RiotApiClient;
import st.tiy.budgetopgg.model.mapper.MatchDtoMatchMapper;
import st.tiy.budgetopgg.repository.MatchRepository;
import st.tiy.budgetopgg.utils.FileLoaderUtil;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MatchServiceTest {

	private static MatchService matchService;
	private static RiotMatchDto exampleMatch;
	private static MatchRepository matchRepository;
	private static MatchDtoMatchMapper mapper;
	private static RiotApiClient apiClient;


	@BeforeAll
	static void setUpBeforeClass() throws IOException {
		mapper = mock(MatchDtoMatchMapper.class);
		matchRepository = mock(MatchRepository.class);
		apiClient = mock(RiotApiClient.class);
		matchService = new MatchService(matchRepository, mapper, apiClient);
		exampleMatch = FileLoaderUtil.loadFile("example/exampleAramMatch.json", RiotMatchDto.class);
	}

	@Test
	void exampleMatchLoadsTest() {
		assertThat(exampleMatch).isNotNull();
	}


}