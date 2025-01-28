package st.tiy.budgetopgg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.budgetopgg.api.Server;
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

	@GetMapping("/{server}/{gameName}/{tagLine}")
	public ResponseEntity<Summoner> getSummoner(@PathVariable Server server, @PathVariable String gameName, @PathVariable String tagLine) {
		Optional<Summoner> summoner = service.getSummoner(server, gameName, tagLine);

		return summoner.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
		               .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/{server}/{gameName}/{tagLine}/update")
	public ResponseEntity<Summoner> updateSummoner(@PathVariable Server server, @PathVariable String gameName, @PathVariable String tagLine) {
		Summoner summoner = service.updateSummoner(server, gameName, tagLine);

		return ResponseEntity.status(HttpStatus.OK).body(summoner);
	}

}
