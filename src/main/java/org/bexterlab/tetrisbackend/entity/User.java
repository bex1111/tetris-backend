package org.bexterlab.tetrisbackend.entity;


public class User {
    private String username;
    private String token;
    private Long points;

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getToken() {
        return token;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public Long getPoints() {
        return points;
    }

    public User setPoints(Long points) {
        this.points = points;
        return this;
    }
}
