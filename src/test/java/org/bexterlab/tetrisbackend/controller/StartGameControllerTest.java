package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.commonmock.LoggerSpy;
import org.bexterlab.tetrisbackend.controller.dto.StartGameDto;
import org.bexterlab.tetrisbackend.controller.mock.StartGameInteractorSpy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StartGameControllerTest {

    @Test
    void controllerTest() {
        LoggerSpy loggerSpy = new LoggerSpy();
        StartGameInteractorSpy startGameInteractor = new StartGameInteractorSpy();
        StartGameController startGameController = new StartGameController(loggerSpy, startGameInteractor);
        final String username = "username";
        StartGameDto startGameDto = new StartGameDto().setUsername(username);
        Assertions.assertEquals(startGameInteractor.token, startGameController.startGame(startGameDto));
        Assertions.assertEquals(username, startGameInteractor.username);
        Assertions.assertEquals(startGameDto, loggerSpy.object);
    }
}