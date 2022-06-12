package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.UserStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserStoreFake implements UserStore {

    public boolean hasGameWithUser = false;
    public boolean hasGameWithUserAndToken = false;
    public long userCount = 2;
    public List<String> usernameList;
    public String hasGameWithUserAndTokenUsername, storePointUserName;
    public String token;
    public Long point;
    public String addPlayerIntoScoreBoardUsername;
    private final List<String> callMethodName;

    public UserStoreFake(List<String> callMethodName) {
        this.callMethodName = callMethodName;
    }

    public UserStoreFake() {
        this.callMethodName = new ArrayList<>();
    }

    @Override
    public boolean hasGameWithUser(String username) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return hasGameWithUser;
    }

    @Override
    public boolean hasGameWithUserAndToken(String username, String token) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.hasGameWithUserAndTokenUsername = username;
        this.token = token;
        return hasGameWithUserAndToken;
    }

    @Override
    public List<String> findUsernames() {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return usernameList;
    }

    @Override
    public void storePoint(String username, Long point) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.storePointUserName = username;
        this.point = point;
    }

    @Override
    public void addPlayerIntoScoreBoard(String username) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.addPlayerIntoScoreBoardUsername = username;
    }

    @Override
    public Map<String, Long> finScoreBoard() {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return Collections.emptyMap();
    }

    @Override
    public long countUser() {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return this.userCount;
    }
}
