package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.core.move.Movement;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControlController {

    private final Logger logger;
    private final ControlInteractor controlInteractor;

    public ControlController(Logger logger, ControlInteractor controlInteractor) {
        this.logger = logger;
        this.controlInteractor = controlInteractor;
    }

    @PostMapping("/control")
    public void control(
            @RequestHeader("x-username") String username,
            @RequestHeader("x-token") String token,
            @RequestParam Movement movement) {
        logger.info(username + " : " + movement);
        controlInteractor.addMovement(username, token, movement);
    }
}
