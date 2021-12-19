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

    public TetrisStepFactoryFake() {
        this.steps = new ArrayList<>();
    }

    @Override
    public TrackElement[][] rotateLeft(TrackElement[][] track) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] rotateRight(TrackElement[][] track) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] moveLeft(TrackElement[][] track) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] moveRight(TrackElement[][] track) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] moveDown(TrackElement[][] track) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] collideElement(TrackElement[][] track) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] clearFullRow(TrackElement[][] track) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] spawnNewElement(TrackElement[][] track, TetrisElement tetrisElement) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        spawnNewTetrisElement = tetrisElement;
        return track;
    }

    @Override
    public TetrisElement drawTetrisElement() {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        return draw;
    }

    @Override
    public Long countPoints(TrackElement[][] track) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        return count;
    }
}
