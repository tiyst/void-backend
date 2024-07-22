package st.tiy.budgetopgg.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.riot.SummonerDTO;

@Mapper(componentModel = "spring")
public interface SummonerDtoSummonerMapper {

	@Mapping(source = "profileIconId", target = "profileIcon")
	@Mapping(source = "summonerLevel", target = "level")
	@Mapping(target = "puuid", ignore = true)
	Summoner mapSummonerDtoToSummoner(SummonerDTO source, @MappingTarget Summoner summoner);

}
