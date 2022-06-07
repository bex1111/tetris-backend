package org.bexterlab.tetrisbackend.controller.dto;

public class PointsDto {

    public String username;
    public Long points;

    public PointsDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public PointsDto setPoints(Long points) {
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
