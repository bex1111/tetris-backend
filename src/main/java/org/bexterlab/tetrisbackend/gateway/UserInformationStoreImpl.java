package org.bexterlab.tetrisbackend.gateway;

import org.bexterlab.tetrisbackend.core.gateway.UserInformationStore;

import java.util.concurrent.ConcurrentHashMap;

public class UserInformationStoreImpl implements UserInformationStore {

    private final ConcurrentHashMap<String, Integer> userRound;

    public UserInformationStoreImpl(ConcurrentHashMap<String, Integer> userRound) {
        this.userRound = userRound;
    }

    @Override

    public int countUserRound(String username) {
        return userRound.getOrDefault(username, 0);
    }

    @Override
    public void saveRound(String username) {
        userRound.put(username, userRound.getOrDefault(username, 0) + 1);
    }
}
