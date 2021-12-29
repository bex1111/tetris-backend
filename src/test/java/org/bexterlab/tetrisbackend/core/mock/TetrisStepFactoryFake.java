package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.ArrayList;
import java.util.List;

public class TetrisStepFactoryFake extends TetrisStepFactory {

    public List<String> steps;
    public TetrisElement draw;
    public TetrisElement spawnNewTetrisElement;
    public Long count;
    public TrackElement[][] rotateLeftTrackElement, rotateRightTrackElement,
            moveLeftTrackElement, moveRightTrackElement,
            moveDownTrackElement, collideElementTrackElement,
            clearFullRowTrackElement, spawnNewElementTrackElement;

    public TetrisStepFactoryFake() {
        this.steps = new ArrayList<>();
    }

    @Override
    public TrackElement[][] rotateLeft(TrackElement[][] track) {
        this.rotateLeftTrackElement = track;
        return track;
    }

    @Override
    public TrackElement[][] rotateRight(TrackElement[][] track) {
        this.rotateRightTrackElement = track;
        return track;
    }

    @Override
    public TrackElement[][] moveLeft(TrackElement[][] track) {
        this.moveLeftTrackElement = track;
        return track;
    }

    @Override
    public TrackElement[][] moveRight(TrackElement[][] track) {
        this.moveRightTrackElement = track;
        return track;
    }

    @Override
    public TrackElement[][] moveDown(TrackElement[][] track) {
        this.moveDownTrackElement = track;
        return track;
    }

    @Override
    public TrackElement[][] collideElement(TrackElement[][] track) {
        this.collideElementTrackElement = track;
        return track;
    }

    @Override
    public TrackElement[][] clearFullRow(TrackElement[][] track) {
        this.clearFullRowTrackElement = track;
        return track;
    }

    @Override
    public TrackElement[][] spawnNewElement(TrackElement[][] track, TetrisElement tetrisElement) {
        this.spawnNewTetrisElement = tetrisElement;
        this.spawnNewElementTrackElement = track;
        return track;
    }

    @Override
    public TetrisElement drawTetrisElement() {
        return draw;
    }

    @Override
    public Long countPoints(TrackElement[][] track) {
        return count;
    }
}
