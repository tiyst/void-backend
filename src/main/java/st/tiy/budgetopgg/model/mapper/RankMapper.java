package st.tiy.budgetopgg.model.mapper;

import com.riotgames.model.LeagueEntryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.budgetopgg.model.domain.summoner.Rank;

@Mapper(componentModel = "spring")
public interface RankMapper {
	@Mapping(source = "rank", target = "division")
	@Mapping(target = "id", ignore = true) // Ignore the id property
	Rank LeagueEntryDTOtoRankMapper(LeagueEntryDTO entry);

	default Rank mapWithLogging(LeagueEntryDTO entry) {
		Rank rank = LeagueEntryDTOtoRankMapper(entry);
		System.out.println("Mapped LeagueEntryDTO to Rank: " + rank);
		return rank;
	}
}
