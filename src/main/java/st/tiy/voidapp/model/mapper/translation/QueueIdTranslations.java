package st.tiy.voidapp.model.mapper.translation;

import java.util.Map;

//Used for matches
public class QueueIdTranslations {

	private static final Map<Integer, String> queueIdToDescription = Map.ofEntries(
			Map.entry(0, "Custom games"),
			Map.entry(2, "5v5 Blind Pick games"),
			Map.entry(4, "5v5 Ranked Solo games"),
			Map.entry(6, "5v5 Ranked Premade games"),
			Map.entry(7, "Co-op vs AI games"),
			Map.entry(8, "3v3 Normal games"),
			Map.entry(9, "3v3 Ranked Flex games"),
			Map.entry(14, "5v5 Draft Pick games"),
			Map.entry(16, "5v5 Dominion Blind Pick games"),
			Map.entry(17, "5v5 Dominion Draft Pick games"),
			Map.entry(25, "Dominion Co-op vs AI games"),
			Map.entry(31, "Co-op vs AI Intro Bot games"),
			Map.entry(32, "Co-op vs AI Beginner Bot games"),
			Map.entry(33, "Co-op vs AI Intermediate Bot games"),
			Map.entry(41, "3v3 Ranked Team games"),
			Map.entry(42, "5v5 Ranked Team games"),
			Map.entry(52, "Co-op vs AI games"),
			Map.entry(61, "5v5 Team Builder games"),
			Map.entry(65, "5v5 ARAM games"),
			Map.entry(67, "ARAM Co-op vs AI games"),
			Map.entry(70, "One for All games"),
			Map.entry(72, "1v1 Snowdown Showdown games"),
			Map.entry(73, "2v2 Snowdown Showdown games"),
			Map.entry(75, "6v6 Hexakill games"),
			Map.entry(76, "Ultra Rapid Fire games"),
			Map.entry(78, "One For All: Mirror Mode games"),
			Map.entry(83, "Co-op vs AI Ultra Rapid Fire games"),
			Map.entry(91, "Doom Bots Rank 1 games"),
			Map.entry(92, "Doom Bots Rank 2 games")
	);

	public static String getDescriptionByQueueId(int queueId) {
		return queueIdToDescription.getOrDefault(queueId, "Unknown Queue");
	}
}
