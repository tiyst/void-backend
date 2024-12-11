package st.tiy.budgetopgg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.budgetopgg.model.domain.summoner.Rank;
import st.tiy.budgetopgg.service.RankService;
import st.tiy.budgetopgg.utils.Dev;

import java.util.List;

@Dev
@RestController
@RequestMapping("/api/rank")
public class RankController {

	private final RankService rankService;

	public RankController(RankService rankService) {
		this.rankService = rankService;
	}

	@GetMapping(path = "/{summonerId}")
	public List<Rank> getRanksBySummonerId(@PathVariable(name = "summonerId") String summonerId) {
		return this.rankService.getRanksBySummonerId(summonerId);
	}

}
