package st.tiy.budgetopgg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.budgetopgg.model.domain.summoner.Summoner;
import st.tiy.budgetopgg.service.SummonerService;

import java.util.Optional;

@RestController
@RequestMapping("/api/summoner")
public class SummonerController {

	private final SummonerService service;

	public SummonerController(SummonerService service) {
		this.service = service;
	}

	@GetMapping("/{gameName}/{tagLine}")
	public ResponseEntity<Summoner> getSummoner(@PathVariable String gameName, @PathVariable String tagLine) {
		Optional<Summoner> summoner = service.getSummoner(gameName, tagLine);

		return summoner.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
		               .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/{gameName}/{tagLine}/update")
	public ResponseEntity<Summoner> updateSummoner(@PathVariable String gameName, @PathVariable String tagLine) {
		Summoner summoner = service.updateSummoner(gameName, tagLine);

		return ResponseEntity.status(HttpStatus.OK).body(summoner);
	}

}
