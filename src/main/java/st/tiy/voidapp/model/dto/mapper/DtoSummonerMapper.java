package st.tiy.voidapp.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.voidapp.model.domain.mastery.ChampionMastery;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.domain.summoner.Rank;
import st.tiy.voidapp.model.domain.summoner.Summoner;
import st.tiy.voidapp.model.dto.DtoChampionMastery;
import st.tiy.voidapp.model.dto.DtoSummoner;
import st.tiy.voidapp.model.dto.DtoTrophy;
import st.tiy.voidapp.trophy.trophies.Trophy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DtoRankMapper.class, DtoMatchMapper.class})
public interface DtoSummonerMapper {

	@Mapping(source = "summoner.puuid", target = "puuid")
	DtoSummoner toDtoSummoner(Summoner summoner, List<Match> matches, List<Trophy> trophies);

	DtoSummoner toDtoSummoner(Summoner summoner, List<Match> matches, List<Rank> ranks,
	                          List<ChampionMastery> championMasteries, List<Trophy> trophies);

	DtoChampionMastery toDtoChampionMastery(ChampionMastery championMastery);

	DtoTrophy toDtoTrophy(Trophy trophy);

}
