package st.tiy.budgetopgg.model.riot.match;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Style {
	private String description;
	private ArrayList<Selection> selections;
	private int style;
}
