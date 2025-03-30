package st.tiy.voidapp.trophy;

import org.springframework.stereotype.Component;
import st.tiy.voidapp.model.domain.summoner.Summoner;
import st.tiy.voidapp.trophy.trophies.Trophy;
import st.tiy.voidapp.trophy.trophies.TrophyBestKda;
import st.tiy.voidapp.trophy.trophies.TrophyHighestDamage;
import st.tiy.voidapp.trophy.trophies.TrophyLongestTimeSpentDead;
import st.tiy.voidapp.trophy.trophies.TrophyMostCastedSpells;
import st.tiy.voidapp.trophy.trophies.TrophyMostCreepScore;
import st.tiy.voidapp.trophy.trophies.TrophyMostDeaths;
import st.tiy.voidapp.trophy.trophies.TrophyMostKills;
import st.tiy.voidapp.trophy.trophies.TrophyMostPings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class TrophyFactory {

	private final Map<Class<? extends Trophy>, Supplier<? extends Trophy>> trophySuppliers = new HashMap<>();

	public TrophyFactory() {
		registerTrophy(TrophyBestKda.class, TrophyBestKda::new);
		registerTrophy(TrophyMostKills.class, TrophyMostKills::new);
		registerTrophy(TrophyMostDeaths.class, TrophyMostDeaths::new);
		registerTrophy(TrophyLongestTimeSpentDead.class, TrophyLongestTimeSpentDead::new);
		registerTrophy(TrophyMostCastedSpells.class, TrophyMostCastedSpells::new);
		registerTrophy(TrophyMostPings.class, TrophyMostPings::new);
		registerTrophy(TrophyMostCreepScore.class, TrophyMostCreepScore::new);
		registerTrophy(TrophyHighestDamage.class, TrophyHighestDamage::new);
	}

	private <T extends Trophy> void registerTrophy(Class<T> type, Supplier<? extends T> supplier) {
		trophySuppliers.put(type, supplier);
	}

	public Trophy createTrophy(Class<? extends Trophy> trophyClass, Summoner summoner) {
		Supplier<? extends Trophy> supplier = trophySuppliers.get(trophyClass);
		if (supplier != null) {
			Trophy trophy = supplier.get();
			trophy.setSummoner(summoner);
			return trophy;
		}
		throw new IllegalArgumentException("No supplier registered for " + trophyClass.getSimpleName());
	}

	public List<Class<? extends Trophy>> getAllTrophyTypes() {
		return new ArrayList<>(trophySuppliers.keySet());
	}

}
