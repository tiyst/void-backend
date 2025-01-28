package st.tiy.budgetopgg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.budgetopgg.api.Region;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.service.MatchService;
import st.tiy.budgetopgg.utils.Dev;

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

}
