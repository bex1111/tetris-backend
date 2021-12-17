package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.UserStore;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.User;

public class UserStoreFake implements UserStore {
    public boolean hasGameWithUser = false;
    public boolean hasGameWithUserAndToken = false;
    public String username;
    public String token;
    public Long point;
    public Game game;

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

    @Override
    public void storePoint(Game game, Long point) {
        this.game = game;
        this.point = point;
    }

    @Override
    public void addPlayerIntoScoreBoard(User user) {
        
    }
}
