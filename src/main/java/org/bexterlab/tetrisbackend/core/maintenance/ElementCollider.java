package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.entity.TrackElement;

import java.util.ArrayList;
import java.util.List;

public class ElementCollider {


    public TrackElement[][] collide(TrackElement[][] track) {
        boolean isCollide = false;
        List<Integer[]> elementIndex = new ArrayList<>();
        for (int i = 0; i < track.length - 1; i++) {
            for (int j = 0; j < track[i].length; j++) {
                if (isElementCollide(track, i, j)) {
                    isCollide = true;
                }
                if (track[i][j].isNotFix) {
                    elementIndex.add(new Integer[]{i, j});
                }
            }
        }
        if (isCollide) {
            transformElemntToPoint(track, elementIndex);
        }
        return track;
    }

    private boolean isElementCollide(TrackElement[][] track, int i, int j) {
        return track[i][j].isNotFix && track[i + 1][j] == TrackElement.POINT;
    }

    private void transformElemntToPoint(TrackElement[][] track, List<Integer[]> elementIndex) {
        elementIndex.forEach(x -> track[x[0]][x[1]] = TrackElement.POINT);
    }
}
