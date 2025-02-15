package st.tiy.voidapp.model.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import st.tiy.voidapp.model.domain.summoner.Rank;
import st.tiy.voidapp.model.dto.DtoRank;

@Mapper(componentModel = "spring")
public interface DtoRankMapper {

	@Mapping(target = "inPromotion", ignore = true)
	@Mapping(source = "rankSeries.losses", target = "promoLosses")
	@Mapping(source = "rankSeries.progress", target = "promoProgress")
	@Mapping(source = "rankSeries.target", target = "promoTarget")
	@Mapping(source = "rankSeries.wins", target = "promoWins")
	DtoRank toDtoRank(Rank rank);

	@AfterMapping
	default void isInPromotion(@MappingTarget DtoRank target, Rank rank) {
		if(rank.getRankSeries() != null) {
			target.setInPromotion(true);
		}
	}
}
