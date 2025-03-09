package st.tiy.voidapp.queue.task.summonerfetch;

import lombok.Builder;
import st.tiy.voidapp.api.Server;

@Builder
public record BasicSummonerProcessTaskParams(Server server, String gameName, String tagLine) {

}
