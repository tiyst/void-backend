package st.tiy.voidapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.voidapp.api.Region;
import st.tiy.voidapp.model.domain.match.Match;
import st.tiy.voidapp.service.MatchService;
import st.tiy.voidapp.utils.Dev;

import java.util.List;

@Dev
@RestController
@RequestMapping("/api/match")
public class MatchController {

	private final MatchService matchService;

	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@GetMapping("/{region}/{puuid}")
	public List<Match> getMatchesByPuuid(@PathVariable("region") Region region, @PathVariable("puuid") String puuid) {
		return this.matchService.getMatchesByPuuid(region, puuid);
	}

	@GetMapping("/{region}/{puuid}/update")
	public List<Match> updateMatchesByPuuid(@PathVariable("region") Region region, @PathVariable("puuid") String puuid) {
		return this.matchService.updateMatchesByPuuid(region, puuid);
	}

}
