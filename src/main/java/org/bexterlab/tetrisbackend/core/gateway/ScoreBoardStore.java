package org.bexterlab.tetrisbackend.core.gateway;

import java.util.Map;

public interface ScoreBoardStore {

    void addPlayerIntoScoreBoard(String username, Long point);

    Map<String, Long> finScoreBoard();
}
