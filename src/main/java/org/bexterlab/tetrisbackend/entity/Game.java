package org.bexterlab.tetrisbackend.entity;

import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.concurrent.ConcurrentLinkedQueue;

public record Game(Player player,
                   TrackElement[][] track,
                   ConcurrentLinkedQueue<Movement> movementQueue,
                   TetrisElements tetrisElements) {


}
