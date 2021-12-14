package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.UserStore;

public class UserStoreFake implements UserStore {
    public boolean hasGameWithUser = false;
    public boolean hasGameWithUserAndToken = false;
    public String username;
    public String token;

    @Override
    public boolean hasGameWithUser(String username) {
        return hasGameWithUser;
    }

    @Override
    public boolean hasGameWithUserAndToken(String username, String token) {
        this.username = username;
        this.token = token;
        return hasGameWithUserAndToken;
    }
}
