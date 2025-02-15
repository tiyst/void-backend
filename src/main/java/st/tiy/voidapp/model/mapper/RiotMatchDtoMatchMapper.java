package st.tiy.voidapp.model.mapper;

import com.riotgames.model.RiotMatchDto;
import com.riotgames.model.match.RiotBanDto;
import com.riotgames.model.match.RiotChallenges;
import com.riotgames.model.match.RiotInfo;
import com.riotgames.model.match.RiotMetadata;
import com.riotgames.model.match.RiotObjectiveDto;
import com.riotgames.model.match.RiotObjectives;
import com.riotgames.model.match.RiotParticipantDto;
import com.riotgames.model.match.RiotPerks;
import com.riotgames.model.match.RiotSelection;
import com.riotgames.model.match.RiotStyle;
import com.riotgames.model.match.RiotTeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.domain.match.team.Challenges;
import st.tiy.voidapp.model.domain.match.team.Objective;
import st.tiy.voidapp.model.domain.match.team.Participant;
import st.tiy.voidapp.model.domain.match.team.Team;
import st.tiy.voidapp.model.domain.match.team.runes.Perks;
import st.tiy.voidapp.model.domain.match.team.runes.PerksSelection;
import st.tiy.voidapp.model.domain.match.team.runes.PerksStyle;

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

	@Mapping(target = "summoner", ignore = true)
	@Mapping(target = "endOfGameResult", ignore = true)
	@Mapping(target = "gameCreation", ignore = true)
	@Mapping(target = "gameDuration", ignore = true)
	@Mapping(target = "gameEndTimestamp", ignore = true)
	@Mapping(target = "gameId", ignore = true)
	@Mapping(target = "gameMode", ignore = true)
	@Mapping(target = "gameName", ignore = true)
	@Mapping(target = "gameStartTimestamp", ignore = true)
	@Mapping(target = "gameType", ignore = true)
	@Mapping(target = "gameVersion", ignore = true)
	@Mapping(target = "mapId", ignore = true)
	@Mapping(target = "participantList", ignore = true)
	@Mapping(target = "platformId", ignore = true)
	@Mapping(target = "queueId", ignore = true)
	@Mapping(target = "teams", ignore = true)
	@Mapping(target = "tournamentCode", ignore = true)
	@Mapping(target = "retrievedDate", ignore = true)
	@Mapping(source = "participants", target = "participantIds")
	void mapMetadataToMatch(@MappingTarget Match match, RiotMetadata metadata);

	@Mapping(target = "matchId", ignore = true)
	@Mapping(target = "summoner", ignore = true)
	@Mapping(target = "dataVersion", ignore = true)
	@Mapping(target = "participantIds", ignore = true)
	@Mapping(target = "retrievedDate", ignore = true)
	@Mapping(source = "participants", target = "participantList")
	void mapInfoToMatch(@MappingTarget Match match, RiotInfo info);

	@Mapping(source = ".", target = ".")
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "challenges", target = "challenges")
	Participant mapToParticipant(RiotParticipantDto participantDto);

	@Mapping(target = "id", ignore = true)
	Objective mapToObjective(RiotObjectiveDto objectiveDto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "participant", ignore = true)
	Challenges mapToChallenges(RiotChallenges challengesDto);

	@Mapping(target = "id", ignore = true)
	@Mapping(source = "statPerks.defense", target = "defense")
	@Mapping(source = "statPerks.offense", target = "offense")
	@Mapping(source = "statPerks.flex", target = "flex")
	Perks mapToPerks(RiotPerks perksDto);

	@Mapping(target = "id", ignore = true)
	PerksStyle mapToPerksStyle(RiotStyle riotStyle);

	@Mapping(target = "id", ignore = true)
	PerksSelection mapToPerksSelection(RiotSelection riotSelection);

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
