package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.core.exception.CanNotMoveException;
import org.bexterlab.tetrisbackend.entity.TrackElement;

import java.util.Arrays;

import static org.bexterlab.tetrisbackend.entity.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.entity.TrackElement.POINT;

public abstract class BaseSideMover {

    private TrackElement[][] deepCopy(TrackElement[][] track) {
        return Arrays.stream(track).map(TrackElement[]::clone).toArray(x -> track.clone());
    }

    private TrackElement[][] moveToSide(TrackElement[][] track, int direction) {
        TrackElement[][] movedTrack = deepCopy(track);
        for (int i = 0; i < movedTrack.length; i++) {
            for (int j = 0; j < movedTrack[i].length; j++) {
                if (track[i][j].isNotFix && !track[i][j + direction].isNotFix) {
                    checkIsElementCollideWithPoint(track, direction, i, j);
                    movedTrack[i][j + direction] = movedTrack[i][j];
                    movedTrack[i][j] = EMPTY;
                }
            }
        }
        return movedTrack;
    }

    private void checkIsElementCollideWithPoint(TrackElement[][] track, int direction, int i, int j) {
        if (track[i][j + direction] == POINT) {
            throw new CanNotMoveException();
        }
    }


    protected final TrackElement[][] move(TrackElement[][] track, int direction) {
        try {
            return moveToSide(track, direction);
        } catch (IndexOutOfBoundsException e) {
            throw new CanNotMoveException(e);
        }
    }
}
