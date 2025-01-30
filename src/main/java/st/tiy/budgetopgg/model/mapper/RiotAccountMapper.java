package st.tiy.budgetopgg.model.mapper;

import com.riotgames.model.RiotAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;

@Mapper(componentModel = "spring")
public interface RiotAccountMapper {

	@Mapping(target = "accountId", ignore = true)
	@Mapping(target = "summonerId", ignore = true)
	@Mapping(target = "server", ignore = true)
	@Mapping(target = "profileIcon", ignore = true)
	@Mapping(target = "level", ignore = true)
	@Mapping(target = "rank", ignore = true)
	@Mapping(target = "masteries", ignore = true)
	@Mapping(target = "lastUpdated", expression = "java( System.currentTimeMillis() / 1000L )")
	Summoner mapAccountDtoToSummoner(RiotAccountDto riotAccountDto);

}
