package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.gateway.UserInformationStore;

import java.util.List;

public class UserInformationStoreFake implements UserInformationStore {

    public int countUserRound = 0;
    public String countUserRoundUsername, saveRoundUsername;
    private final List<String> callMethodName;


    public UserInformationStoreFake(List<String> callMethodName) {
        this.callMethodName = callMethodName;
    }

    @Override
    public int countUserRound(String username) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.countUserRoundUsername = username;
        return countUserRound;
    }

    @Override
    public void saveRound(String username) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.saveRoundUsername = username;
    }
}
