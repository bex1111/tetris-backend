package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.maintenance.ElementCollider;
import org.bexterlab.tetrisbackend.core.maintenance.FullRowCleaner;
import org.bexterlab.tetrisbackend.core.maintenance.NewElementSpawner;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElementLottery;
import org.bexterlab.tetrisbackend.core.move.DownMover;
import org.bexterlab.tetrisbackend.core.move.LeftMover;
import org.bexterlab.tetrisbackend.core.move.RightMover;
import org.bexterlab.tetrisbackend.core.rotate.LeftRotator;
import org.bexterlab.tetrisbackend.core.rotate.RightRotator;
import org.bexterlab.tetrisbackend.entity.TrackElement;

import java.util.Optional;

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

    public TrackElement[][] spawnNewElement(TrackElement[][] track, NewElementSpawner.TetrisElement tetrisElement) {
        return new NewElementSpawner().swapNew(track, tetrisElement);
    }

    public Optional<NewElementSpawner.TetrisElement> drawNewElementIfNotExist(TrackElement[][] track) {
        return new TetrisElementLottery().drawIfNotElementInTheTrack(track);
    }


}
