package st.tiy.voidapp.model.mapper;

import com.riotgames.model.RiotMatchDto;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.utils.FileLoaderUtil;

import static org.assertj.core.api.Assertions.assertThat;


class MatchDtoRiotMatchMapperTest {

	private static RiotMatchDtoMatchMapper mapper;
	private static RiotMatchDto riotMatchDto;

	@BeforeAll
	static void setUp() throws IOException {
		riotMatchDto = FileLoaderUtil.loadFile("example/exampleAramMatch.json", RiotMatchDto.class);
		mapper = new RiotMatchDtoMatchMapperImpl();
	}

	@Test
	void matchMapsCorrectly() {
		Match match = mapper.mapToMatch(riotMatchDto);

		assertThat(match).isNotNull();
	}

}