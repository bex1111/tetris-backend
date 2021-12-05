package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;

import java.util.List;

public class GameStoreFake implements GameStore {

    public boolean hasGameWithUser;
    public Game game;
    public TetrisElement tetrisNewElement;

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
    public Game storeNewTetrisElement(Game game, TetrisElement tetrisElement) {
        this.tetrisNewElement = tetrisElement;
        return game;
    }

    @Override
    public void storeNewTrack(Game game, TrackElement[][] track) {

    }

    @Override
    public boolean hasGame() {
        return false;
    }
}
