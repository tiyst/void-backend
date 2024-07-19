package st.tiy.budgetopgg.model.riot.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Metadata {
	private String dataVersion;
	private String matchId;
	private ArrayList<String> participants;
}
