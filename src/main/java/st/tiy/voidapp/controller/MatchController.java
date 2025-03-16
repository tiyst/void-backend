package st.tiy.voidapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.voidapp.api.Region;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.model.dto.match.DtoMatch;
import st.tiy.voidapp.service.MatchService;
import st.tiy.voidapp.utils.Dev;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

	private final MatchService matchService;

	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@GetMapping("/{puuid}")
	public List<DtoMatch> getMoreMatches(@PathVariable("puuid") String puuid,
	                                     @RequestParam int matchesSize) {
		return this.matchService.getPageableMatchesByPuuid(puuid, matchesSize);
	}

	@Dev
	@GetMapping("/{puuid}/get")
	public List<Match> getMatchesByPuuid(@PathVariable("puuid") String puuid) {
		return this.matchService.getInitialMatchesByPuuid(puuid);
	}

	@Dev
	@GetMapping("/{region}/{puuid}/update")
	public List<Match> updateMatchesByPuuid(@PathVariable("region") Region region, @PathVariable("puuid") String puuid) {
		return this.matchService.updateMatchesByPuuid(region, puuid);
	}

}
