package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.core.TrackElement;
import org.bexterlab.tetrisbackend.core.exception.CanNotMoveException;

import java.util.Arrays;

import static org.bexterlab.tetrisbackend.core.TrackElement.EMPTY;

public abstract class BaseSideMover {

    private TrackElement[][] deepCopy(TrackElement[][] track) {
        return Arrays.stream(track).map(TrackElement[]::clone).toArray(x -> track.clone());
    }

    private TrackElement[][] moveToSide(TrackElement[][] track, int direction) {
        TrackElement[][] movedTrack = deepCopy(track);
        for (int i = 0; i < movedTrack.length; i++) {
            for (int j = 0; j < movedTrack[i].length; j++) {
                if (track[i][j].canMoveToTheSide && track[i][j + direction] == EMPTY) {
                    movedTrack[i][j + direction] = movedTrack[i][j];
                    movedTrack[i][j] = EMPTY;
                }
            }
        }
        return movedTrack;
    }


    protected final TrackElement[][] move(TrackElement[][] track, int direction) {
        try {
            return moveToSide(track, direction);
        } catch (IndexOutOfBoundsException e) {
            throw new CanNotMoveException(e);
        }
    }
}
