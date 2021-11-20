package org.bexterlab.tetrisbackend.entity;

import java.util.LinkedList;

public record Game(Player player,
                   TrackElement[][] track,
                   LinkedList<Movement> movementQueue,
                   TetrisElements tetrisElements) {
    

}
