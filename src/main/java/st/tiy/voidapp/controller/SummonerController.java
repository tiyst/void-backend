package st.tiy.voidapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.voidapp.api.Server;
import st.tiy.voidapp.model.dto.DtoSummoner;
import st.tiy.voidapp.service.SummonerService;

@RestController
@RequestMapping("/api/summoner")
public class SummonerController {

	private final SummonerService service;

	public SummonerController(SummonerService service) {
		this.service = service;
	}

	@GetMapping("/{server}/{gameName}/{tagLine}")
	public ResponseEntity<DtoSummoner> getSummoner(@PathVariable Server server,
	                                               @PathVariable String gameName,
	                                               @PathVariable String tagLine) {
		DtoSummoner summoner = service.getSummoner(server, gameName, tagLine);

		return ResponseEntity.status(HttpStatus.OK)
		                     .headers(getGenericHeaders())
		                     .body(summoner);
	}

	@GetMapping("/{server}/{gameName}/{tagLine}/update")
	public ResponseEntity<DtoSummoner> updateSummoner(@PathVariable Server server,
	                                                  @PathVariable String gameName,
	                                                  @PathVariable String tagLine) {
		DtoSummoner summoner = service.updateSummoner(server, gameName, tagLine);

		return ResponseEntity.status(HttpStatus.OK)
		                     .headers(getGenericHeaders())
		                     .body(summoner);
	}

	private HttpHeaders getGenericHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		return headers;
	}
}
