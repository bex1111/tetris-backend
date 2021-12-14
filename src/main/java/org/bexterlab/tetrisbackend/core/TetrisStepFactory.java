package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.maintenance.*;
import org.bexterlab.tetrisbackend.core.move.DownMover;
import org.bexterlab.tetrisbackend.core.move.LeftMover;
import org.bexterlab.tetrisbackend.core.move.RightMover;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.core.rotate.LeftRotator;
import org.bexterlab.tetrisbackend.core.rotate.RightRotator;

public class TetrisStepFactory {


    public TrackElement[][] rotateLeft(TrackElement[][] track) {
        return new LeftRotator().rotate(track);
    }

    public TrackElement[][] rotateRight(TrackElement[][] track) {
        return new RightRotator().rotate(track);
    }

    public TrackElement[][] moveLeft(TrackElement[][] track) {
        return new LeftMover().moveLeft(track);
    }

    public TrackElement[][] moveRight(TrackElement[][] track) {
        return new RightMover().moveRight(track);
    }

    public TrackElement[][] moveDown(TrackElement[][] track) {
        return new DownMover().moveDown(track);
    }

    public TrackElement[][] collideElement(TrackElement[][] track) {
        return new ElementCollider().collide(track);
    }

    public TrackElement[][] clearFullRow(TrackElement[][] track) {
        return new FullRowCleaner().clean(track);
    }

    public TrackElement[][] spawnNewElement(TrackElement[][] track, TetrisElement tetrisElement) {
        return tetrisElement.spawnNew(track);
    }

    public TetrisElement drawTetrisElement() {
        return new TetrisElementLottery().draw();
    }

    public Long countPoints(TrackElement[][] track) {
        return new PointCounter().count(track);
    }
}
