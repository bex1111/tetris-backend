package org.bexterlab.tetrisbackend.core.rotate;

import org.bexterlab.tetrisbackend.core.exception.CanNotRotateException;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.Arrays;

public abstract class BaseRotator {

    public TrackElement[][] rotate(TrackElement[][] track) {
        try {
            return rotateIterator(track);
        } catch (IndexOutOfBoundsException e) {
            throw new CanNotRotateException(e);
        }
    }

    private TrackElement[][] rotateIterator(TrackElement[][] track) {
        TrackElement[][] rotatedTrack = deepCopy(track);
        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[i].length; j++) {
                if (track[i][j].isNotFix) {
                    checkIsRotateColliedWithPoint(rotatedTrack, i, j);
                    rotatedTrack = replaceElement(rotatedTrack, i, j);
                }
            }
        }
        return rotatedTrack;
    }

    private void checkIsRotateColliedWithPoint(TrackElement[][] rotatedTrack, int i, int j) {
        if (isRotateColliedWithPoint(rotatedTrack, i, j)) {
            throw new CanNotRotateException();
        }
    }

    protected abstract boolean isRotateColliedWithPoint(TrackElement[][] rotatedTrack, int i, int j);

    protected abstract TrackElement[][] replaceElement(TrackElement[][] rotatedTrack, int i, int j);

    private TrackElement[][] deepCopy(TrackElement[][] track) {
        return Arrays.stream(track).map(TrackElement[]::clone).toArray(x -> track.clone());
    }
}
