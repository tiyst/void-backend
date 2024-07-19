package st.tiy.budgetopgg.model.riot.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Team {
	private ArrayList<BanDto> bans;
	private Objectives objectives;
	private int teamId;
	private boolean win;
}
