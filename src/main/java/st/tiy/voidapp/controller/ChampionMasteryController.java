package st.tiy.voidapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.voidapp.api.Server;
import st.tiy.voidapp.model.domain.mastery.ChampionMastery;
import st.tiy.voidapp.service.ChampionMasteryService;
import st.tiy.voidapp.utils.Dev;

import java.util.List;

@Dev
@RestController
@RequestMapping("/api/mastery")
public class ChampionMasteryController {

	private final ChampionMasteryService championMasteryService;

	public ChampionMasteryController(ChampionMasteryService championMasteryService) {
		this.championMasteryService = championMasteryService;
	}

	@GetMapping("/{server}/{puuid}")
	public List<ChampionMastery> getMasteryForPuuid(@PathVariable("server") Server server,
	                                                @PathVariable("puuid") String puuid) {
		return championMasteryService.getMasteryByPuuid(server, puuid);
	}

	@GetMapping("/{server}/{puuid}/champion/{championId}")
	public ChampionMastery getMasteryForPuuidAndChampionId(@PathVariable("server") Server server,
	                                                             @PathVariable("puuid") String puuid,
	                                                             @PathVariable("championId") String championId) {
		return championMasteryService.getMasteryByPuuidAndChampionId(server, puuid, championId);
	}
}
