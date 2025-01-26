package st.tiy.budgetopgg.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoRank {

	private String leagueId;
	private String queueType;
	private String tier;
	private String division;

	private int leaguePoints;
	private int wins;
	private int losses;

	private boolean freshBlood;
	private boolean hotStreak;
	private boolean veteran;
	private boolean inactive;

	private boolean isInPromotion;
	private int promoLosses;
	private String promoProgress;
	private int promoTarget;
	private int promoWins;

}
