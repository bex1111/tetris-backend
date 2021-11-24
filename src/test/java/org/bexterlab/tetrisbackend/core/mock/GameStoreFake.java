package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.maintenance.NewElementSpawner;
import org.bexterlab.tetrisbackend.entity.Game;

import java.util.List;

public class GameStoreFake implements GameStore {

    public boolean hasGameWithUser;
    public Game game;

    @Override
    public boolean hasGameWithUser(String username) {
        return hasGameWithUser;
    }

    @Override
    public void createNewGame(Game game) {
        this.game = game;
    }

    @Override
    public List<Game> getGames() {
        return List.of(game);
    }

    @Override
    public void storeNewTetrisElement(Game game, NewElementSpawner.TetrisElement tetrisElement) {
        
    }

    @Override
    public boolean hasGame() {
        return false;
    }
}
