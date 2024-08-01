package st.tiy.budgetopgg.model.domain.match;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


import java.util.List;


@Getter
@Setter
@Entity
public class Match {

	// can't use riots internal datatypes, just store what we need from what they have
	@Id
	private String matchId; // game id to use as key? needed?
	// match type = string or enum?

	@OneToMany
	@ElementCollection
	private List<Team> teams; // if team won, go to team.win, set true;
	// holds List<Participant> participants; // name of players in the match

	private List<Integer> bans; // go to BanDto, get championId;

	// TODO find in riot's API if the match was remade & store in boolean
	private int gameLength;
}