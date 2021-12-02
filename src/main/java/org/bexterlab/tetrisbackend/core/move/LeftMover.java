package org.bexterlab.tetrisbackend.core.move;

public class LeftMover extends BaseSideMover {

    public TrackElement[][] moveLeft(TrackElement[][] track) {
        return move(track, -1);
    }
}
