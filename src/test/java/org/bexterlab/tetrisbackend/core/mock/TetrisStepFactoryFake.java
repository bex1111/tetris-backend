package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.maintenance.NewElementSpawner;
import org.bexterlab.tetrisbackend.entity.TrackElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TetrisStepFactoryFake extends TetrisStepFactory {

    public List<String> steps;

    public TetrisStepFactoryFake() {
        this.steps = new ArrayList<>();
    }

    @Override
    public TrackElement[][] rotateLeft(TrackElement[][] track) {
        steps.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] rotateRight(TrackElement[][] track) {
        steps.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] moveLeft(TrackElement[][] track) {
        steps.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] moveRight(TrackElement[][] track) {
        steps.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] moveDown(TrackElement[][] track) {
        steps.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] collideElement(TrackElement[][] track) {
        steps.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] clearFullRow(TrackElement[][] track) {
        steps.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public TrackElement[][] spawnNewElement(TrackElement[][] track, NewElementSpawner.TetrisElement tetrisElement) {
        steps.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return track;
    }

    @Override
    public Optional<NewElementSpawner.TetrisElement> drawNewElementIfNotExist(TrackElement[][] track) {
        steps.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return Optional.empty();
    }
}
