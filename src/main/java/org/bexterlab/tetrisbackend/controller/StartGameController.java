package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.controller.dto.StartGameDto;
import org.bexterlab.tetrisbackend.core.gateway.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartGameController {

    private final Logger logger;
    private final StartGameInteractor startGameInteractor;

    public StartGameController(Logger logger, StartGameInteractor startGameInteractor) {
        this.logger = logger;
        this.startGameInteractor = startGameInteractor;
    }

    @PostMapping("/startGame")
    public String startGame(@RequestBody StartGameDto startGameDto) {
        logger.info(startGameDto);
        return startGameInteractor.start(startGameDto.getUsername());
    }
}
