package st.tiy.budgetopgg.model.mapper;

import com.riotgames.model.RiotMatchDto;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.utils.FileLoaderUtil;

import static org.assertj.core.api.Assertions.assertThat;


class MatchDtoRiotMatchMapperTest {

	private static MatchDtoMatchMapper mapper;
	private static RiotMatchDto riotMatchDto;

	@BeforeAll
	static void setUp() throws IOException {
		riotMatchDto = FileLoaderUtil.loadFile("example/exampleMatch.json", RiotMatchDto.class);
		mapper = new MatchDtoMatchMapperImpl();
	}

	@Test
	void matchMapsCorrectly() {
		Match match = mapper.mapToMatch(riotMatchDto);

		assertThat(match).isNotNull();
	}

}