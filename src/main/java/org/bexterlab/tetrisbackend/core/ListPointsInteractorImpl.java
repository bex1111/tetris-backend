package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.controller.ListPointsInteractor;

import java.util.Map;

public class ListPointsInteractorImpl implements ListPointsInteractor {

    private final ScoreBoardStore scoreBoardStore;

    public ListPointsInteractorImpl(ScoreBoardStore scoreBoardStore) {
        this.scoreBoardStore = scoreBoardStore;
    }

    @Override
    public Map<String, Long> list() {
        return scoreBoardStore.finScoreBoard();
    }
}
