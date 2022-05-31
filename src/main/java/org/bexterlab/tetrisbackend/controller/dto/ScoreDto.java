package org.bexterlab.tetrisbackend.controller.dto;

public class ScoreDto {

    public String username;
    public Long points;

    public ScoreDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public ScoreDto setPoints(Long points) {
        this.points = points;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Long getPoints() {
        return points;
    }
}
