package st.tiy.voidapp.queue.task.summonerfetch;

import st.tiy.voidapp.api.Server;

public record BasicSummonerProcessTaskParams(Server server, String gameName, String tagLine) {

}
