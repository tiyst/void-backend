package st.tiy.voidapp.model.mapper.translation;

import java.util.Map;

//Used for ranks
public class QueueTypeTranslations {

	private static final Map<String, String> queueTypeTranslations = Map.of(
			"RANKED_SOLO_5x5", "Ranked Solo/Duo",
			"RANKED_TEAM_5x5", "Ranked Flex"
	);

	public static String getDescriptionByQueueType(String queueType) {
		return queueTypeTranslations.getOrDefault(queueType, "Unknown Queue");
	}


}
