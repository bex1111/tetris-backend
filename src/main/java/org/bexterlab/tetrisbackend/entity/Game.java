package org.bexterlab.tetrisbackend.entity;

import org.bexterlab.tetrisbackend.core.TrackElement;

public record Game(String username, String token, TrackElement[][] track) {


}
