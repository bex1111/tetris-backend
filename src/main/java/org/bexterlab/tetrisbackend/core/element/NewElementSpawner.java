package org.bexterlab.tetrisbackend.core.element;

import org.bexterlab.tetrisbackend.core.TrackElement;

public class NewElementSpawner {


    public TrackElement[][] swapNewIfNotExist(TrackElement[][] track, TetrisElement tetrisElement) {
        return tetrisElement.spawnNew(track);
    }

    public enum TetrisElement {

        SQUARE() {
            @Override
            public TrackElement[][] spawnNew(TrackElement[][] track) {
                int startPoint = track[0].length / 2;
                track[0][startPoint - 1] = TrackElement.SQUARE_POINT;
                track[0][startPoint] = TrackElement.SQUARE_POINT;
                track[1][startPoint - 1] = TrackElement.SQUARE_POINT;
                track[1][startPoint] = TrackElement.SQUARE_POINT;
                return track;
            }
        },
        RIGHT_L {
            @Override
            public TrackElement[][] spawnNew(TrackElement[][] track) {
                int startPoint = track[0].length / 2 - 1;
                track[0][startPoint + 1] = TrackElement.THREE_LONG_ELEMENT_UP_RIGHT;
                track[0][startPoint] = TrackElement.THREE_LONG_ELEMENT_UP_MIDDLE;
                track[1][startPoint] = TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE;
                track[2][startPoint] = TrackElement.THREE_LONG_ELEMENT_DOWN_MIDDLE;
                return track;
            }
        },
        LEFT_L {
            @Override
            public TrackElement[][] spawnNew(TrackElement[][] track) {
                int startPoint = track[0].length / 2;
                track[0][startPoint - 1] = TrackElement.THREE_LONG_ELEMENT_UP_LEFT;
                track[0][startPoint] = TrackElement.THREE_LONG_ELEMENT_UP_MIDDLE;
                track[1][startPoint] = TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE;
                track[2][startPoint] = TrackElement.THREE_LONG_ELEMENT_DOWN_MIDDLE;
                return track;
            }
        }, RIGHT_PYRAMID {
            @Override
            public TrackElement[][] spawnNew(TrackElement[][] track) {
                int startPoint = track[0].length / 2 - 1;
                track[0][startPoint] = TrackElement.THREE_LONG_ELEMENT_UP_MIDDLE;
                track[1][startPoint] = TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE;
                track[1][startPoint + 1] = TrackElement.THREE_LONG_ELEMENT_MIDDLE_RIGHT;
                track[2][startPoint] = TrackElement.THREE_LONG_ELEMENT_DOWN_MIDDLE;
                return track;
            }
        }, LEFT_PYRAMID {
            @Override
            public TrackElement[][] spawnNew(TrackElement[][] track) {
                int startPoint = track[0].length / 2;
                track[0][startPoint] = TrackElement.THREE_LONG_ELEMENT_UP_MIDDLE;
                track[1][startPoint] = TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE;
                track[1][startPoint - 1] = TrackElement.THREE_LONG_ELEMENT_MIDDLE_LEFT;
                track[2][startPoint] = TrackElement.THREE_LONG_ELEMENT_DOWN_MIDDLE;
                return track;
            }
        }, STRAIGHT {
            @Override
            public TrackElement[][] spawnNew(TrackElement[][] track) {
                int startPoint = track[0].length / 2 - 1;
                track[0][startPoint] = TrackElement.THREE_LONG_ELEMENT_UP_MIDDLE;
                track[1][startPoint] = TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE;
                track[2][startPoint] = TrackElement.THREE_LONG_ELEMENT_DOWN_MIDDLE;
                return track;
            }
        };

        public abstract TrackElement[][] spawnNew(TrackElement[][] track);

    }

}
