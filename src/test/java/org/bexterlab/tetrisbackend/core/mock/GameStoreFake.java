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
    public Movement findNextMovement;
    public TrackElement[][] findTrackByUser, storeNewTrack;
    public String findTrackByUserUsername, findNextMovementUsername,
            storeNewTrackUsername, removeGameUsername;

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
        this.storeNewTrack = track;
        this.storeNewTrackUsername = username;
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
        this.removeGameUsername = username;
    }

    @Override
    public Movement findNextMovement(String username) {
        this.findNextMovementUsername = username;
        return findNextMovement;
    }

    @Override
    public TrackElement[][] findTrackByUser(String username) {
        findTrackByUserUsername = username;
        return findTrackByUser;
    }

    @Override
    public TetrisElement findNextTetrisElement(String username) {
        return null;
    }
}
