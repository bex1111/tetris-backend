package org.bexterlab.tetrisbackend.entity;

import java.util.Queue;

public record Game(String username, String token, TrackElement[][] track, Queue<Movement> movementQueue) {


}
