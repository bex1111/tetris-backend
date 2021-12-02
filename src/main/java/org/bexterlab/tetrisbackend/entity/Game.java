package org.bexterlab.tetrisbackend.entity;

import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.LinkedList;

public record Game(Player player,
                   TrackElement[][] track,
                   LinkedList<Movement> movementQueue,
                   TetrisElements tetrisElements) {


}
