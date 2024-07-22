package st.tiy.budgetopgg.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.riot.AccountDto;

@Mapper(componentModel = "spring")
public interface AccountDtoSummonerMapper {

	@Mapping(target = "lastUpdated", expression = "java( System.currentTimeMillis() / 1000L )")
//	@Mapping(source = ".", target = "lastUpdated", qualifiedByName = "currentTime")
	Summoner mapAccountDtoToSummoner(AccountDto accountDto);

	@Named("currentTime")
	default long currentUnixTime(AccountDto accountDto) {
		return System.currentTimeMillis() / 1000L;
	}

}
