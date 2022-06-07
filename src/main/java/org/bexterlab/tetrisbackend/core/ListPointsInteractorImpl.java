package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.controller.ListPointsInteractor;
import org.bexterlab.tetrisbackend.entity.User;

import java.util.List;

public class ListPointsInteractorImpl implements ListPointsInteractor {

    private final UserStore userStore;

    public ListPointsInteractorImpl(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public List<User> list() {
        return userStore.findUsers();
    }
}
