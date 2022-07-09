package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.gateway.UserInformationStore;

import java.util.List;

public class UserInformationStoreFake implements UserInformationStore {

    public int countUserRound = 0;
    private final List<String> callMethodName;

    public UserInformationStoreFake(List<String> callMethodName) {
        this.callMethodName = callMethodName;
    }

    @Override
    public int countUserRound(String username) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return countUserRound;
    }
}
