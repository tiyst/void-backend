package st.tiy.voidapp.model.mapper.translation;


import java.util.Map;

public class GameModeTranslations {

	private static Map<String, String> modeTranslations = Map.ofEntries(
			Map.entry("CLASSIC", "Classic Summoner's Rift games"),
			Map.entry("ODIN", "Dominion/Crystal Scar games"),
			Map.entry("ARAM", "ARAM games"),
			Map.entry("TUTORIAL", "Tutorial games"),
			Map.entry("URF", "URF games"),
			Map.entry("DOOMBOTSTEEMO", "Doom Bot games"),
			Map.entry("ONEFORALL", "One for All games"),
			Map.entry("ASCENSION", "Ascension games"),
			Map.entry("FIRSTBLOOD", "Snowdown Showdown games"),
			Map.entry("KINGPORO", "Legend of the Poro King games"),
			Map.entry("SIEGE", "Nexus Siege games"),
			Map.entry("ASSASSINATE", "Blood Hunt Assassin games"),
			Map.entry("ARSR", "All Random Summoner's Rift games"),
			Map.entry("DARKSTAR", "Dark Star: Singularity games"),
			Map.entry("STARGUARDIAN", "Star Guardian Invasion games"),
			Map.entry("PROJECT", "PROJECT: Hunters games"),
			Map.entry("GAMEMODEX", "Nexus Blitz games"),
			Map.entry("ODYSSEY", "Odyssey: Extraction games"),
			Map.entry("NEXUSBLITZ", "Nexus Blitz games"),
			Map.entry("ULTBOOK", "Ultimate Spellbook games")
	);

	public static String translateGameMode(String gameMode) {
		return modeTranslations.getOrDefault(gameMode, "Unknown game mode");
	}


}
