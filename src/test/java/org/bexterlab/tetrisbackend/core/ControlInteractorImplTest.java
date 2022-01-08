package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.exception.NotYourGameException;
import org.bexterlab.tetrisbackend.core.exception.TooManyMovementException;
import org.bexterlab.tetrisbackend.core.mock.MovementStoreFake;
import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.move.Movement.MOVE_LEFT;

class ControlInteractorImplTest {

    private UserStoreFake userStore;
    private MovementStoreFake movementStore;

    @BeforeEach
    void setUp() {
        userStore = new UserStoreFake();
        movementStore = new MovementStoreFake();
    }

    @Test
    void notYourGameTest() {
        ControlInteractorImpl controlInteractor =
                new ControlInteractorImpl(movementStore, userStore);
        userStore.hasGameWithUserAndToken = false;
        Assertions.assertThrows(NotYourGameException.class,
                () -> controlInteractor.addMovement("test", "test", MOVE_LEFT));
    }

    @Test
    void tooManyMovementTest() {
        ControlInteractorImpl controlInteractor =
                new ControlInteractorImpl(movementStore, userStore);
        userStore.hasGameWithUserAndToken = true;
        movementStore.movementCount = 31;
        Assertions.assertThrows(TooManyMovementException.class,
                () -> controlInteractor.addMovement("test", "test", MOVE_LEFT));
    }

    @Test
    void successTest() {
        ControlInteractorImpl controlInteractor =
                new ControlInteractorImpl(movementStore, userStore);
        userStore.hasGameWithUserAndToken = true;
        movementStore.movementCount = 30;
        Movement moveLeft = MOVE_LEFT;
        String username = "username";
        String token = "token";
        controlInteractor.addMovement(username, token, moveLeft);
        Assertions.assertEquals(username, movementStore.username);
        Assertions.assertEquals(moveLeft, movementStore.movement);
        Assertions.assertEquals(username, userStore.hasGameWithUserAndTokenUsername);
        Assertions.assertEquals(token, userStore.token);
    }
}