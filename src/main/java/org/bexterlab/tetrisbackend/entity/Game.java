package org.bexterlab.tetrisbackend.entity;

import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.concurrent.ConcurrentLinkedQueue;

public record Game(User user,
                   TrackElement[][] track,
                   ConcurrentLinkedQueue<Movement> movementQueue,
                   TetrisElements tetrisElements) {


}
