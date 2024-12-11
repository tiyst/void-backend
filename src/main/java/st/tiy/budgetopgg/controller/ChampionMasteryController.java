package st.tiy.budgetopgg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.budgetopgg.model.domain.mastery.ChampionMastery;
import st.tiy.budgetopgg.service.ChampionMasteryService;
import st.tiy.budgetopgg.utils.Dev;

import java.util.List;

@Dev
@RestController
@RequestMapping("/api/mastery")
public class ChampionMasteryController {

	private final ChampionMasteryService championMasteryService;

	public ChampionMasteryController(ChampionMasteryService championMasteryService) {
		this.championMasteryService = championMasteryService;
	}

	@GetMapping("/{puuid}")
	public List<ChampionMastery> getMasteryForPuuid(@PathVariable("puuid") String puuid) {
		return championMasteryService.getMasteryByPuuid(puuid);
	}

	@GetMapping("/{puuid}/champion/{championId}")
	public List<ChampionMastery> getMasteryForPuuidAndChampionId(@PathVariable("puuid") String puuid,
	                                                             @PathVariable("championId") String championId) {
		return championMasteryService.getMasteryByPuuidAndChampionId(puuid, championId);
	}
}
