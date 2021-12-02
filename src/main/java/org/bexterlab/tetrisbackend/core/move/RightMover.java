package org.bexterlab.tetrisbackend.core.move;

public class RightMover extends BaseSideMover {

    public TrackElement[][] moveRight(TrackElement[][] track) {
        return move(track, 1);
    }
}
