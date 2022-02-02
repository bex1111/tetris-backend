package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.commonmock.LoggerSpy;
import org.bexterlab.tetrisbackend.controller.mock.ControlInteractorSpy;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ControlControllerTest {


    @Test
    void controllerTest() {
        LoggerSpy loggerSpy = new LoggerSpy();
        ControlInteractorSpy controlInteractorSpy = new ControlInteractorSpy();
        ControlController controlController = new ControlController(loggerSpy, controlInteractorSpy);
        final String username = "username";
        final String token = "token";
        final Movement movement = Movement.MOVE_LEFT;

        controlController.control(username, token, movement);

        Assertions.assertEquals(username, controlInteractorSpy.username);
        Assertions.assertEquals(token, controlInteractorSpy.token);
        Assertions.assertEquals(movement, controlInteractorSpy.movement);
        Assertions.assertEquals("Username : " + username + ", movement: " + movement, loggerSpy.object);
    }
}