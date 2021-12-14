package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.entity.Game;

public interface UserStore {

    boolean hasGameWithUser(String username);

    boolean hasGameWithUserAndToken(String username, String token);

    void storePoint(Game game, Long point);
}
