package org.bexterlab.tetrisbackend.gateway.store;

import org.bexterlab.tetrisbackend.core.gateway.ScoreBoardStore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoardStoreImpl implements ScoreBoardStore {

    private final ConcurrentHashMap<String, Long> scoreBoard;

    public ScoreBoardStoreImpl(ConcurrentHashMap<String, Long> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    @Override
    public void addPlayerIntoScoreBoard(String username, Long point) {
        scoreBoard.put(username,
                scoreBoard.getOrDefault(username, 0L) +
                        point);
    }

    @Override
    public Map<String, Long> finScoreBoard() {
        return scoreBoard;
    }

}
