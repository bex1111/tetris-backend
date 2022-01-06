package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.UserStore;

import java.util.List;

public class UserStoreFake implements UserStore {
    public boolean hasGameWithUser = false;
    public boolean hasGameWithUserAndToken = false;
    public List<String> usernameList;
    public String hasGameWithUserAndTokenUsername, storePointUserName;
    public String token;
    public Long point;
    public String addPlayerIntoScoreBoardUsername;

    @Override
    public boolean hasGameWithUser(String username) {
        return hasGameWithUser;
    }

    @Override
    public boolean hasGameWithUserAndToken(String username, String token) {
        this.hasGameWithUserAndTokenUsername = username;
        this.token = token;
        return hasGameWithUserAndToken;
    }

    @Override
    public List<String> findUsernames() {
        return usernameList;
    }


    @Override
    public void storePoint(String username, Long point) {
        this.storePointUserName = username;
        this.point = point;
    }

    @Override
    public void addPlayerIntoScoreBoard(String username) {
        this.addPlayerIntoScoreBoardUsername = username;
    }
}
