package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.core.exception.CannotMoveException;

import java.util.Arrays;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.POINT;

public abstract class BaseSideMover {

    protected TrackElement[][] deepCopy(TrackElement[][] track) {
        return Arrays.stream(track).map(TrackElement[]::clone).toArray(x -> track.clone());
    }

    protected abstract TrackElement[][] moveToSide(TrackElement[][] track);

    protected void checkIsElementCollideWithPoint(TrackElement[][] track, int direction, int i, int j) {
        if (track[i][j + direction] == POINT) {
            throw new CannotMoveException();
        }
    }

    protected final TrackElement[][] move(TrackElement[][] track) {
        try {
            return moveToSide(track);
        } catch (IndexOutOfBoundsException e) {
            throw new CannotMoveException(e);
        }
    }
}
