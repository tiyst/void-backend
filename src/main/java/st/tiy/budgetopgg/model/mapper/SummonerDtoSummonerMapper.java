package st.tiy.budgetopgg.model.mapper;

import com.riotgames.model.RiotSummonerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;

@Mapper(componentModel = "spring")
public interface SummonerDtoSummonerMapper {

	@Mapping(source = "profileIconId", target = "profileIcon")
	@Mapping(source = "summonerLevel", target = "level")
	@Mapping(source = "id", target = "summonerId")
	@Mapping(target = "puuid", ignore = true)
	Summoner mapSummonerDtoToSummoner(RiotSummonerDTO source, @MappingTarget Summoner summoner);

}
