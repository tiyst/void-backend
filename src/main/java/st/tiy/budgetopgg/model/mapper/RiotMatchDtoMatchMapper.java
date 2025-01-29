package st.tiy.budgetopgg.model.mapper;

import com.riotgames.model.RiotMatchDto;
import com.riotgames.model.match.RiotBanDto;
import com.riotgames.model.match.RiotChallenges;
import com.riotgames.model.match.RiotInfo;
import com.riotgames.model.match.RiotMetadata;
import com.riotgames.model.match.RiotObjectiveDto;
import com.riotgames.model.match.RiotObjectives;
import com.riotgames.model.match.RiotParticipantDto;
import com.riotgames.model.match.RiotPerks;
import com.riotgames.model.match.RiotTeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.model.domain.match.team.Challenges;
import st.tiy.budgetopgg.model.domain.match.team.Objective;
import st.tiy.budgetopgg.model.domain.match.team.Participant;
import st.tiy.budgetopgg.model.domain.match.team.Team;
import st.tiy.budgetopgg.model.domain.match.team.runes.Perks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface RiotMatchDtoMatchMapper {

	// TODO Missions

	@Mapping(target = "id", ignore = true)
	default Match mapToMatch(RiotMatchDto riotMatchDto) {
		Match match = new Match();

		mapMetadataToMatch(match, riotMatchDto.getMetadata());
		mapInfoToMatch(match, riotMatchDto.getInfo());

		return match;
	}

	@Mapping(source = "participants", target = "participantIds")
	void mapMetadataToMatch(@MappingTarget Match match, RiotMetadata metadata);

	@Mapping(source = "participants", target = "participantList")
	void mapInfoToMatch(@MappingTarget Match match, RiotInfo info);

	@Mapping(source = ".", target = ".")
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "challenges", target = "challenges")
	Participant mapToParticipant(RiotParticipantDto participantDto);

	Objective mapToObjective(RiotObjectiveDto objectiveDto);

	Challenges mapToChallenges(RiotChallenges challengesDto);

	@Mapping(source = "statPerks.defense", target = "defense")
	@Mapping(source = "statPerks.offense", target = "offense")
	@Mapping(source = "statPerks.flex", target = "flex")
	Perks mapToPerks(RiotPerks perksDto);

	//TODO generify mapping to maps with T
	default Map<String, Objective> mapObjectives(RiotObjectives objectives) {
		Map<String, Objective> result = new HashMap<>();
		try {
			Field[] fields = objectives.getClass().getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				Object fieldValue = field.get(objectives);

				if (fieldValue instanceof RiotObjectiveDto value) {
					result.put(fieldName, mapToObjective(value));
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	default Team mapToTeam(RiotTeamDto riotTeamDto) {
		Team team = new Team();
		team.setObjectives(mapObjectives(riotTeamDto.getObjectives()));
		team.setChampionBans(mapBans(riotTeamDto.getBans()));
		team.setWin(riotTeamDto.isWin());
		team.setTeamId(riotTeamDto.getTeamId());

		return team;
	}

	default List<Integer> mapBans(ArrayList<RiotBanDto> bans) {
		return bans.stream()
			.sorted(Comparator.comparingInt(RiotBanDto::getPickTurn))
			.map(RiotBanDto::getChampionId)
			.toList();
	}

	default Map<String, Integer> mapToFieldMap(Object source) {
		Map<String, Integer> result = new HashMap<>();
		try {
			Field[] fields = source.getClass().getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				Object fieldValue = field.get(source);

				Integer r;
				if (fieldValue instanceof Integer value) {
					r = value;
				} else if (fieldValue instanceof Number number) {
					r = number.intValue();
				} else if (fieldValue instanceof Boolean bool) {
					r = Boolean.TRUE.equals(bool) ? 1 : 0;
				} else {
					r = 0;
				}

				result.put(fieldName, r);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
}
