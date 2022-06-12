package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.controller.ListPointsInteractor;

import java.util.Map;

public class ListPointsInteractorImpl implements ListPointsInteractor {

    private final UserStore userStore;

    public ListPointsInteractorImpl(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public Map<String, Long> list() {
        return userStore.finScoreBoard();
    }
}
