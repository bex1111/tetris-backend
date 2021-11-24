package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.entity.TrackElement;

import java.util.ArrayList;
import java.util.List;

public class ElementCollider {

    private boolean isCollide;
    private final List<Integer[]> elementIndex;

    public ElementCollider() {
        this.isCollide = false;
        this.elementIndex = new ArrayList<>();
    }


    public TrackElement[][] collide(TrackElement[][] track) {
        checkElementCollideWithOtherPoiont(track);
        checkElementReachLastRow(track);
        if (isCollide) {
            transformElemntToPoint(track, elementIndex);
        }
        return track;
    }

    private void checkElementReachLastRow(TrackElement[][] track) {
        for (int j = 0; j < track[track.length - 1].length; j++) {
            if (track[track.length - 1][j].isNotFix) {
                isCollide = true;
                elementIndex.add(new Integer[]{track.length - 1, j});
            }
        }
    }

    private void checkElementCollideWithOtherPoiont(TrackElement[][] track) {
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
    }

    private boolean isElementCollide(TrackElement[][] track, int i, int j) {
        return track[i][j].isNotFix && track[i + 1][j] == TrackElement.POINT;
    }

    private void transformElemntToPoint(TrackElement[][] track, List<Integer[]> elementIndex) {
        elementIndex.forEach(x -> track[x[0]][x[1]] = TrackElement.POINT);
    }
}
