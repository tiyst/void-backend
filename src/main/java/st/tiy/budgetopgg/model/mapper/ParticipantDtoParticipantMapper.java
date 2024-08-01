package st.tiy.budgetopgg.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.riotgames.model.match.ParticipantDto;
import st.tiy.budgetopgg.model.domain.match.Participant;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;

@Mapper(componentModel = "spring")

public interface ParticipantDtoParticipantMapper {

	@Mapping(source = "summoner", target = "summoner")
	Participant mapToParticipant(ParticipantDto dto, Summoner summoner);

	//@Mapping(target = "summoner", ignore = true)

}
