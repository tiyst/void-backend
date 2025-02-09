package st.tiy.budgetopgg.model.dto.mapper;

import org.mapstruct.Mapper;
import st.tiy.budgetopgg.model.domain.mastery.ChampionMastery;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.model.dto.DtoChampionMastery;
import st.tiy.budgetopgg.model.dto.DtoSummoner;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DtoRankMapper.class, DtoMatchMapper.class})
public interface DtoSummonerMapper {

	DtoSummoner toDtoSummoner(Summoner summoner, List<Match> matches);

	DtoSummoner toDtoSummoner(Summoner summoner, List<Match> matches, List<Rank> ranks,
	                          List<ChampionMastery> championMasteries);

	DtoChampionMastery toDtoChampionMastery(ChampionMastery championMastery);


}
