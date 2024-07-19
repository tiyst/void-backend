package st.tiy.budgetopgg.model.riot;

import lombok.Getter;
import lombok.Setter;
import st.tiy.budgetopgg.model.riot.match.Info;
import st.tiy.budgetopgg.model.riot.match.Metadata;

@Getter
@Setter
public class MatchDto {
	private Metadata metadata;
	private Info info;
}
