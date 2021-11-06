package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.GameStore;

public class GameStoreFake implements GameStore {

    public boolean hasGameWithUser;
    @Override
    public boolean hasGameWithUser(String username) {
        return hasGameWithUser;
    }

    @Override
    public String createNewGame(String username) {
        return "test";
    }
}
