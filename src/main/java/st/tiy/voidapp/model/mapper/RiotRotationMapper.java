package st.tiy.voidapp.model.mapper;

import com.riotgames.model.rotation.RiotChampionInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.voidapp.model.domain.rotation.Rotation;

@Mapper(componentModel = "spring")
public interface RiotRotationMapper {

	@Mapping(source = "freeChampionIdsForNewPlayers", target = "rotationForNewPlayers")
	@Mapping(source = "freeChampionIds", target = "rotation")
	Rotation mapToRotation(RiotChampionInfo riotChampionInfo);

}
