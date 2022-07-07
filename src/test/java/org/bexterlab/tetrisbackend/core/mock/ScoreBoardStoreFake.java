package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.ScoreBoardStore;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ScoreBoardStoreFake implements ScoreBoardStore {

    public String addPlayerIntoScoreBoardUsername;
    public Long addPlayerIntoScoreBoardPoint;
    private final List<String> callMethodName;

    public ScoreBoardStoreFake(List<String> callMethodName) {
        this.callMethodName = callMethodName;
    }

    @Override
    public void addPlayerIntoScoreBoard(String username, Long point) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.addPlayerIntoScoreBoardUsername = username;
        this.addPlayerIntoScoreBoardPoint = point;
    }

    @Override
    public Map<String, Long> finScoreBoard() {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return Collections.emptyMap();
    }


}
