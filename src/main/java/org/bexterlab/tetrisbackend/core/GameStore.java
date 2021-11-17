package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.entity.Game;

import java.util.List;

public interface GameStore {
    boolean hasGameWithUser(String username);

    void createNewGame(Game game);

    List<Game> getGames();
}
