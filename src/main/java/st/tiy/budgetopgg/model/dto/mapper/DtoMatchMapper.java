package st.tiy.budgetopgg.model.dto.mapper;

import org.mapstruct.Mapper;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.model.domain.match.team.runes.Perks;
import st.tiy.budgetopgg.model.dto.match.DtoMatch;
import st.tiy.budgetopgg.model.dto.match.DtoPerks;

@Mapper(componentModel = "spring")
public interface DtoMatchMapper {

	DtoMatch toDtoMatch(Match match);

	DtoPerks toDtoPerks(Perks perks);

}
