package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.exception.CannotMoveException;
import org.bexterlab.tetrisbackend.core.exception.CannotRotateException;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.List;

import static java.util.Objects.nonNull;

public class TetrisStepFactoryFake extends TetrisStepFactory {

    public TetrisElement draw;
    public TetrisElement spawnNewTetrisElement;
    public CannotMoveException cannotMoveException;
    public CannotRotateException cannotRotateException;
    public Long count;
    private final List<String> callMethodName;
    public TrackElement[][] rotateLeftTrackElement, rotateRightTrackElement,
            moveLeftTrackElement, moveRightTrackElement,
            moveDownTrackElement, collideElementTrackElement,
            clearFullRowTrackElement, spawnNewElementTrackElement,
            countPointsTrackElement;

    public TetrisStepFactoryFake(List<String> callMethodName) {
        this.callMethodName = callMethodName;
    }

    @Override
    public TrackElement[][] rotateLeft(TrackElement[][] track) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.rotateLeftTrackElement = track;
        throwExceptionIfNotNull(cannotRotateException);
        return track;
    }

    @Override
    public TrackElement[][] rotateRight(TrackElement[][] track) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.rotateRightTrackElement = track;
        throwExceptionIfNotNull(cannotRotateException);
        return track;
    }

    @Override
    public TrackElement[][] moveLeft(TrackElement[][] track) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.moveLeftTrackElement = track;
        throwExceptionIfNotNull(cannotMoveException);
        return track;
    }

    @Override
    public TrackElement[][] moveRight(TrackElement[][] track) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.moveRightTrackElement = track;
        throwExceptionIfNotNull(cannotMoveException);
        return track;
    }

    @Override
    public TrackElement[][] moveDown(TrackElement[][] track) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.moveDownTrackElement = track;
        return track;
    }

    @Override
    public TrackElement[][] collideElement(TrackElement[][] track) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.collideElementTrackElement = track;
        return track;
    }

    @Override
    public TrackElement[][] clearFullRow(TrackElement[][] track) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.clearFullRowTrackElement = track;
        return track;
    }

    @Override
    public TrackElement[][] spawnNewElement(TetrisElement tetrisElement, TrackElement[][] track) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.spawnNewTetrisElement = tetrisElement;
        this.spawnNewElementTrackElement = track;
        return track;
    }

    @Override
    public TetrisElement drawTetrisElement() {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return draw;
    }

    @Override
    public Long countPoints(TrackElement[][] track) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.countPointsTrackElement = track;
        return count;
    }

    private void throwExceptionIfNotNull(RuntimeException runtimeException) {
        if (nonNull(runtimeException)) {
            throw runtimeException;
        }
    }
}
