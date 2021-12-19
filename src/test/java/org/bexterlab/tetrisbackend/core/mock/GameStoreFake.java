package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;

import java.util.List;

public class GameStoreFake implements GameStore {


    public boolean hasGame = false;
    public Game game;
    public TetrisElement tetrisNewElement;

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
    public void storeNewTetrisElement(String username, TetrisElement tetrisElement) {

    }

    @Override
    public void storeNewTrack(Game game, TrackElement[][] track) {

    }

    @Override
    public void storeNewTrack(String username, TrackElement[][] track) {

    }

    @Override
    public boolean hasGame() {
        return hasGame;
    }

    @Override
    public void removeGame(Game game) {

    }

    @Override
    public void removeGame(String username) {

    }

    @Override
    public Movement findNextMovement(String username) {
        return null;
    }

    @Override
    public TrackElement[][] findTrackByUser(String username) {
        return new TrackElement[0][];
    }

    @Override
    public TetrisElement findNextTetrisElement(String username) {
        return null;
    }
}
