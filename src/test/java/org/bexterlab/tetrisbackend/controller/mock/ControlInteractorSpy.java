package org.bexterlab.tetrisbackend.controller.mock;

import org.bexterlab.tetrisbackend.controller.ControlInteractor;
import org.bexterlab.tetrisbackend.core.move.Movement;

public class ControlInteractorSpy implements ControlInteractor {

    public String username;
    public String token;
    public Movement movement;

    @Override
    public void addMovement(String username, String token, Movement movement) {
        this.username = username;
        this.token = token;
        this.movement = movement;
    }
}
