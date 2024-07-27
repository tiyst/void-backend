package st.tiy.budgetopgg.controller;

import com.riotgames.model.MatchDto;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.budgetopgg.service.MatchService;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/api/match")
public class MatchController {

	private final MatchService matchService;

	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@GetMapping("/{puuid}")
	public List<MatchDto> getMatchesByPuuid(@PathVariable("puuid") String puuid) {
		return this.matchService.getMatchesByPuuid(puuid);
	}

}
