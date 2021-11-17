package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.exception.CoreException;
import org.bexterlab.tetrisbackend.entity.Game;

public class TrackHandler {

    private final GameStore gameStore;


    public TrackHandler(GameStore gameStore) {
        this.gameStore = gameStore;
    }

    public void move() {
        this.gameStore.getGames().forEach(this::moveTrack);
    }

    private void moveTrack(Game game) {
        try {
            
        } catch (CoreException e) {
            //do nothing
        }
    }
}
