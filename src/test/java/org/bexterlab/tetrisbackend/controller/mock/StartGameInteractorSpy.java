package org.bexterlab.tetrisbackend.controller.mock;

import org.bexterlab.tetrisbackend.controller.StartGameInteractor;

import java.util.UUID;

public class StartGameInteractorSpy implements StartGameInteractor {

    public String username;
    public String token;

    public StartGameInteractorSpy() {
        this.token = UUID.randomUUID().toString();
    }

    @Override
    public String start(String username) {
        this.username = username;
        return token;
    }
}
