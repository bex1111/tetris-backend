package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.controller.ControlInteractor;
import org.bexterlab.tetrisbackend.core.exception.NotYourGameException;
import org.bexterlab.tetrisbackend.core.exception.TooManyMovementException;
import org.bexterlab.tetrisbackend.core.move.Movement;

public class ControlInteractorImpl implements ControlInteractor {

    private final MovementStore movementStore;
    private final UserStore userStore;

    public ControlInteractorImpl(MovementStore movementStore,
                                 UserStore userStore) {
        this.movementStore = movementStore;
        this.userStore = userStore;
    }

    @Override
    public void addMovement(String username, String token, Movement movement) {
        if (!userStore.hasGameWithUserAndToken(username, token)) {
            throw new NotYourGameException();
        }
        if (movementStore.countMovement(username) > 30) {
            throw new TooManyMovementException();
        }
        movementStore.addNew(username, movement);
    }
}
