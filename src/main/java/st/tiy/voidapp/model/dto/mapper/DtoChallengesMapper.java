package st.tiy.voidapp.model.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import st.tiy.voidapp.model.domain.match.team.Challenges;
import st.tiy.voidapp.model.dto.match.DtoChallenges;

@Mapper(
		componentModel = "spring",
		unmappedSourcePolicy = ReportingPolicy.IGNORE,
		unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DtoChallengesMapper {

	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "fistBumpParticipation", target = "fistBumpParticipation")
	@Mapping(source = "flawlessAces", target = "flawlessAces")
	@Mapping(source = "skillshotsDodged", target = "skillshotsDodged")
	@Mapping(source = "skillshotsHit", target = "skillshotsHit")
	@Mapping(source = "perfectGame", target = "perfectGame")
	@Mapping(source = "epicMonsterSteals", target = "epicMonsterSteals")
	@Mapping(source = "epicMonsterStolenWithoutSmite", target = "epicMonsterStolenWithoutSmite")
	@Mapping(source = "jungleCsBefore10Minutes", target = "jungleCsBefore10Minutes")
	@Mapping(source = "killsUnderOwnTurret", target = "killsUnderOwnTurret")
	@Mapping(source = "hadOpenNexus", target = "hadOpenNexus")
	@Mapping(source = "maxCsAdvantageOnLaneOpponent", target = "maxCsAdvantageOnLaneOpponent")
	@Mapping(source = "multiKillOneSpell", target = "multiKillOneSpell")
	@Mapping(source = "poroExplosions", target = "poroExplosions")
	@Mapping(source = "quickCleanse", target = "quickCleanse")
	@Mapping(source = "teamDamagePercentage", target = "teamDamagePercentage")
	DtoChallenges toDtoChallenges(Challenges challenges);

}
