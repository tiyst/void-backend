package st.tiy.budgetopgg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
	public ResponseEntity<Summoner> getSummoner(@PathVariable String gameName, @PathVariable String tagLine) {
		Summoner summoner = service.getSummoner(gameName, tagLine);

		return ResponseEntity.status(HttpStatus.OK).body(summoner);
	}

}
