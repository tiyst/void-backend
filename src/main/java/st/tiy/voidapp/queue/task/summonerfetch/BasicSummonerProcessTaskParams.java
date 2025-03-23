package st.tiy.voidapp.queue.task.summonerfetch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import st.tiy.voidapp.api.Server;
import st.tiy.voidapp.queue.task.VoidTaskParameters;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BasicSummonerProcessTaskParams implements VoidTaskParameters {
	private Server server;
	private String gameName;
	private String tagLine;
}
