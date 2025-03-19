package st.tiy.voidapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.voidapp.api.Server;
import st.tiy.voidapp.model.domain.summoner.Rank;
import st.tiy.voidapp.service.RankService;
import st.tiy.voidapp.utils.Dev;

import java.util.List;

@Dev
@RestController
@RequestMapping("/api/rank")
public class RankController {

	private final RankService rankService;

	public RankController(RankService rankService) {
		this.rankService = rankService;
	}

	@GetMapping(path = "/{server}/{summonerId}")
	public List<Rank> getRanksBySummonerId(@PathVariable(name = "server") Server server, @PathVariable(name = "summonerId") String summonerId) {
		return this.rankService.pullRanksBySummonerId(server, summonerId);
	}

}
