package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;

import java.util.ArrayList;
import java.util.List;

public class GameStoreFake implements GameStore {

    public boolean hasGame = false;
    public Game game;
    private final List<String> callMethodName;
    public TetrisElement storeNewTetrisElement, findNextTetrisElement;
    public Movement findNextMovement;
    public TrackElement[][] findTrackByUser, storeNewTrack;
    public String findTrackByUserUsername, findNextMovementUsername,
            storeNewTrackUsername, removeGameUsername,
            findNextTetrisElementUsername, storeNewTetrisElementUsername;

    public GameStoreFake(List<String> callMethodName) {
        this.callMethodName = callMethodName;
    }

    public GameStoreFake() {
        this.callMethodName = new ArrayList<>();
    }

    @Override
    public void createNewGame(Game game) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.game = game;
    }

    @Override
    public List<Game> getGames() {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return List.of(game);
    }

    @Override
    public void storeNewTetrisElement(String username, TetrisElement tetrisElement) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.storeNewTetrisElementUsername = username;
        this.storeNewTetrisElement = tetrisElement;
    }

    @Override
    public void storeNewTrack(String username, TrackElement[][] track) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.storeNewTrack = track;
        this.storeNewTrackUsername = username;
    }

    @Override
    public boolean hasGame() {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return hasGame;
    }

    @Override
    public void removeGame(String username) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.removeGameUsername = username;
    }

    @Override
    public Movement findNextMovement(String username) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.findNextMovementUsername = username;
        return findNextMovement;
    }

    @Override
    public TrackElement[][] findTrackByUser(String username) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        findTrackByUserUsername = username;
        return findTrackByUser;
    }

    @Override
    public TetrisElement findNextTetrisElement(String username) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.findNextTetrisElementUsername = username;
        return findNextTetrisElement;
    }
}
