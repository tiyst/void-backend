package st.tiy.budgetopgg.model.mapper;

import com.riotgames.model.MatchDto;
import com.riotgames.model.match.BanDto;
import com.riotgames.model.match.Info;
import com.riotgames.model.match.Metadata;
import com.riotgames.model.match.ObjectiveDto;
import com.riotgames.model.match.Objectives;
import com.riotgames.model.match.ParticipantDto;
import com.riotgames.model.match.TeamDto;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.model.domain.match.team.Challenges;
import st.tiy.budgetopgg.model.domain.match.team.Objective;
import st.tiy.budgetopgg.model.domain.match.team.Participant;
import st.tiy.budgetopgg.model.domain.match.team.Team;

@Mapper(componentModel = "spring")
public interface MatchDtoMatchMapper {

	// TODO Missions

	@Mapping(target = "id", ignore = true)
	default Match mapToMatch(MatchDto matchDto) {
		Match match = new Match();

		mapMetadataToMatch(match, matchDto.getMetadata());
		mapInfoToMatch(match, matchDto.getInfo());

		return match;
	}

	@Mapping(source = "participants", target = "participantIds")
	void mapMetadataToMatch(@MappingTarget Match match, Metadata metadata);

	@Mapping(source = "participants", target = "participantList")
	void mapInfoToMatch(@MappingTarget Match match, Info info);

	@Mapping(source = ".", target = ".")
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "challenges", target = "challenges")
	Participant mapToParticipant(ParticipantDto participantDto);

	Objective mapToObjective(ObjectiveDto objectiveDto);

	@Mapping(source = "_12AssistStreakCount", target = "assistStreakCount12")
	Challenges mapToChallenges(com.riotgames.model.match.Challenges challengesDto);

	//TODO generify mapping to maps with T
	default Map<String, Objective> mapObjectives(Objectives objectives) {
		Map<String, Objective> result = new HashMap<>();
		try {
			Field[] fields = objectives.getClass().getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				Object fieldValue = field.get(objectives);

				if (fieldValue instanceof ObjectiveDto value) {
					result.put(fieldName, mapToObjective(value));
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	default Team mapToTeam(TeamDto teamDto) {
		Team team = new Team();
		team.setObjectives(mapObjectives(teamDto.getObjectives()));
		team.setChampionBans(mapBans(teamDto.getBans()));
		team.setWin(teamDto.isWin());
		team.setTeamId(teamDto.getTeamId());

		return team;
	}

	default List<Integer> mapBans(ArrayList<BanDto> bans) {
		return bans.stream()
			.sorted(Comparator.comparingInt(BanDto::getPickTurn))
			.map(BanDto::getChampionId)
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
