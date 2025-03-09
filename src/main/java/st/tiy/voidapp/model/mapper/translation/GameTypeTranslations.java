package st.tiy.voidapp.model.mapper.translation;

import java.util.Map;

public class GameTypeTranslations {
	private static final Map<String, String> gameTypeToDescription = Map.of(
			"CUSTOM_GAME", "Custom games",
			"TUTORIAL_GAME", "Tutorial games",
			"MATCHED_GAME", "all other games"
	);

	public static String getDescriptionByGameType(String gameType) {
		return gameTypeToDescription.getOrDefault(gameType, "Unknown Game type");
	}

}
