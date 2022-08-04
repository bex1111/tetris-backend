package org.bexterlab.tetrisbackend.controller.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StartGameDtoUnitTest {

    @Test
    void getterSetterTest() {
        StartGameDto startGameDto = new StartGameDto();
        String username = "test";
        startGameDto.setUsername(username);
        Assertions.assertEquals(username, startGameDto.getUsername());
    }
}