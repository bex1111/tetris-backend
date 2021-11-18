package org.bexterlab.tetrisbackend.core.rotate;

import org.bexterlab.tetrisbackend.core.exception.CanNotRotateException;
import org.bexterlab.tetrisbackend.entity.TrackElement;

import java.util.Arrays;

import static org.bexterlab.tetrisbackend.entity.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.entity.TrackElement.POINT;

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
                if (track[i][j] != EMPTY) {
                    checkIsRotetColliedWithPoint(track, rotatedTrack, i, j);
                    rotatedTrack = replaceElement(rotatedTrack, track, i, j);
                }
            }
        }
        return rotatedTrack;
    }

    private void checkIsRotetColliedWithPoint(TrackElement[][] track, TrackElement[][] rotatedTrack, int i, int j) {
        if (rotatedTrack
                [i + track[i][j].rotateLeftRowIndex]
                [j + track[i][j].rotateLeftColumnIndex] == POINT) {
            throw new CanNotRotateException();
        }
    }

    protected abstract TrackElement[][] replaceElement(TrackElement[][] rotatedTrack, TrackElement[][] track, int i, int j);

    private TrackElement[][] deepCopy(TrackElement[][] track) {
        return Arrays.stream(track).map(TrackElement[]::clone).toArray(x -> track.clone());
    }
}
