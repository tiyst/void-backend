package st.tiy.budgetopgg.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.service.RankService;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/api/rank")
public class RankController {

	private final RankService rankService;

	public RankController(RankService rankService) {
		this.rankService = rankService;
	}

	@GetMapping
	public List<Rank> getRanksBySummonerId(@RequestParam(name = "summonerId") String summonerId) {
		return this.rankService.getRanksBySummonerId(summonerId);
	}

}
