package st.tiy.budgetopgg.model.mapper;

import com.riotgames.model.RiotAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;

@Mapper(componentModel = "spring")
public interface RiotAccountMapper {

	@Mapping(target = "lastUpdated", expression = "java( System.currentTimeMillis() / 1000L )")
	Summoner mapAccountDtoToSummoner(RiotAccountDto riotAccountDto);

}
