package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.controller.ControlInteractor;
import org.bexterlab.tetrisbackend.core.exception.PleaseControllYourGameException;
import org.bexterlab.tetrisbackend.entity.Movement;

public class ControlInteractorImpl implements ControlInteractor {

    private final GameStore gameStore;

    public ControlInteractorImpl(GameStore gameStore) {
        this.gameStore = gameStore;
    }

    @Override
    public void addMovement(String username, String token, Movement movement) {
        if (gameStore.hasGameWithUserAndToken(username, token)) {
            throw new PleaseControllYourGameException();
        }
        gameStore.addNewMovement(username, movement);
    }
}
