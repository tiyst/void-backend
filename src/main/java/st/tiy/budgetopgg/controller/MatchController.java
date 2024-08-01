package st.tiy.budgetopgg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.budgetopgg.model.domain.match.Match;
import st.tiy.budgetopgg.service.MatchService;

import java.util.Optional;


@RestController
@RequestMapping("/api/match")
public class MatchController {

	private final MatchService matchService;

	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@GetMapping("/{matchId}")
	public ResponseEntity<Match> getMatchById(@PathVariable String matchId) {
		Optional<Match> matchOptional = matchService.getMatchById(matchId);
		return ResponseEntity.of(matchOptional);
	}
}
