package st.tiy.budgetopgg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.service.SummonerService;

@RestController
@RequestMapping("/summoner")
public class SummonerController {

	private final SummonerService service;

	public SummonerController(SummonerService service) {
		this.service = service;
	}

	@GetMapping("/{gameName}/{tagLine}")
	public Summoner getSummoner(@PathVariable String gameName, @PathVariable String tagLine) {
		return service.getSummoner(gameName, tagLine);
	}

}
