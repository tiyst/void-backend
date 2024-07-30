package st.tiy.budgetopgg.model.mapper;

import com.riotgames.model.rotation.ChampionInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.budgetopgg.model.domain.rotation.Rotation;

@Mapper(componentModel = "spring")
public interface RotationMapper {

	@Mapping(source = "freeChampionIdsForNewPlayers", target = "rotationForNewPlayers")
	@Mapping(source = "freeChampionIds", target = "rotation")
	Rotation mapToRotation(ChampionInfo championInfo);

}
