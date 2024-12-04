package st.tiy.budgetopgg.model.mapper;

import com.riotgames.model.MatchDto;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.utils.FileLoaderUtil;

import static org.assertj.core.api.Assertions.assertThat;


class MatchDtoMatchMapperTest {

	private static MatchDtoMatchMapper mapper;
	private static MatchDto matchDto;

	@BeforeAll
	static void setUp() throws IOException {
		matchDto = FileLoaderUtil.loadFile("example/exampleMatch.json", MatchDto.class);
		mapper = new MatchDtoMatchMapperImpl();
	}

	@Test
	void matchMapsCorrectly() {
		Match match = mapper.mapToMatch(matchDto);

		assertThat(match).isNotNull();
	}

}