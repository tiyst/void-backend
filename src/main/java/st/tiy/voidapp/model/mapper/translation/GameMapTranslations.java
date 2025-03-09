package st.tiy.voidapp.model.mapper.translation;

import java.util.Map;

public class GameMapTranslations {

	public static final String UNKNOWN_MAP = "Unknown Map";
	private static final Map<Integer, String> mapTranslations = Map.ofEntries(
			Map.entry(1, "Summoner's Rift"),
			Map.entry(2, "Summoner's Rift"),
			Map.entry(3, "The Proving Grounds"),
			Map.entry(4, "Twisted Treeline"),
			Map.entry(8, "The Crystal Scar"),
			Map.entry(10, "Twisted Treeline"),
			Map.entry(11, "Summoner's Rift"),
			Map.entry(12, "Howling Abyss"),
			Map.entry(14, "Butcher's Bridge"),
			Map.entry(16, "Cosmic Ruins"),
			Map.entry(18, "Valoran City Park"),
			Map.entry(19, "Substructure 43"),
			Map.entry(20, "Crash Site"),
			Map.entry(21, "Nexus Blitz"),
			Map.entry(22, "Convergence"),
			Map.entry(30, "Rings of Wrath")
	);

	public static String translateGameMap(int mapId) {
		return mapTranslations.getOrDefault(mapId, UNKNOWN_MAP);
	}

}
