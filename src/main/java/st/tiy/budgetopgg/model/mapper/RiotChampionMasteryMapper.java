package st.tiy.budgetopgg.model.mapper;

import com.riotgames.model.mastery.RiotChampionMastery;
import com.riotgames.model.mastery.RiotNextSeasonMilestone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.budgetopgg.model.domain.mastery.ChampionMastery;
import st.tiy.budgetopgg.model.domain.mastery.MasteryMilestone;

@Mapper(componentModel = "spring")
public interface RiotChampionMasteryMapper {

	@Mapping(source = "nextSeasonMilestone", target = "masteryMilestone")
	@Mapping(target = "id", ignore = true)
	ChampionMastery mapToChampionMastery(RiotChampionMastery championMastery);

	@Mapping(target = "id", ignore = true)
	MasteryMilestone mapToMasteryMilestone(RiotNextSeasonMilestone championMastery);

}
