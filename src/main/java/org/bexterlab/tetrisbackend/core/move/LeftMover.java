package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.core.TrackElement;

public class LeftMover extends BaseSideMover {

    public TrackElement[][] moveLeft(TrackElement[][] track) {
        return move(track, -1);
    }
}
