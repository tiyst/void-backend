package st.tiy.voidapp.model.mapper;

import com.riotgames.model.RiotLeagueEntryDTO;
import com.riotgames.model.league.RiotMiniSeriesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.voidapp.model.domain.summoner.Rank;
import st.tiy.voidapp.model.domain.summoner.RankSeries;

@Mapper(componentModel = "spring")
public interface RiotRankMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "summoner", ignore = true)
	@Mapping(source = "rank", target = "division")
	@Mapping(source = "miniSeries", target = "rankSeries")
	Rank mapToRank(RiotLeagueEntryDTO riotLeagueEntryDTO);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "rank", ignore = true)
	RankSeries mapToRankSeries(RiotMiniSeriesDTO riotMiniSeriesDTO);

}
