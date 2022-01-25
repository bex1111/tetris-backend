package org.bexterlab.tetrisbackend.controller.dto;


public class StartGameDto {

    private String username;

    public String getUsername() {
        return username;
    }

    public StartGameDto setUsername(String username) {
        this.username = username;
        return this;
    }
}
