package org.bexterlab.tetrisbackend.core.element;

import org.bexterlab.tetrisbackend.entity.TrackElement;

import java.util.Arrays;

import static org.bexterlab.tetrisbackend.entity.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.entity.TrackElement.POINT;

public class FullRowCleaner {

    public TrackElement[][] clean(TrackElement[][] track) {
        for (TrackElement[] row : track) {
            if (Arrays.stream(row).allMatch(x -> POINT == x)) {
                Arrays.fill(row, EMPTY);
            }
        }
        return track;
    }
}
