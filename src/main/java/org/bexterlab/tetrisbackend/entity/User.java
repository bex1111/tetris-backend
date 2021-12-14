package org.bexterlab.tetrisbackend.entity;

public record User(String username,
                   String token,
                   Long points) {
}
