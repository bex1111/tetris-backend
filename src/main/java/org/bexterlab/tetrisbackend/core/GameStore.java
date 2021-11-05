package org.bexterlab.tetrisbackend.core;

public interface GameStore {
    boolean hasGameWithUser(String username);

    String createNewGame(String username);
}
