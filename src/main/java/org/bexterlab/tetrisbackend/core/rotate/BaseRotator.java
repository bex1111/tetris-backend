package org.bexterlab.tetrisbackend.core.rotate;

import org.bexterlab.tetrisbackend.core.TrackElement;
import org.bexterlab.tetrisbackend.core.exception.CanNotMoveException;

import java.util.Arrays;

import static org.bexterlab.tetrisbackend.core.TrackElement.EMPTY;

public abstract class BaseRotator {

    public TrackElement[][] rotate(TrackElement[][] track) {
        try {
            return rotateIterator(track);
        } catch (IndexOutOfBoundsException e) {
            throw new CanNotMoveException(e);
        }
    }

    private TrackElement[][] rotateIterator(TrackElement[][] track) {
        TrackElement[][] rotatedTrack = deepCopy(track);
        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[i].length; j++) {
                if (track[i][j] != EMPTY) {
                    rotatedTrack = replaceElement(rotatedTrack, track, i, j);
                }
            }
        }
        return rotatedTrack;
    }

    protected abstract TrackElement[][] replaceElement(TrackElement[][] rotatedTrack, TrackElement[][] track, int i, int j);

    private TrackElement[][] deepCopy(TrackElement[][] track) {
        return Arrays.stream(track).map(TrackElement[]::clone).toArray(x -> track.clone());
    }
}
