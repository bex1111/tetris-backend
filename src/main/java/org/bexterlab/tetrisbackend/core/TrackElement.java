package org.bexterlab.tetrisbackend.core;

public enum TrackElement {

    POINT(0, 0, 0, 0, false) {
        @Override
        public TrackElement getLeftNewType() {
            return POINT;
        }

        @Override
        public TrackElement getRightNewType() {
            return POINT;
        }
    },
    EMPTY(0, 0, 0, 0, false) {
        @Override
        public TrackElement getLeftNewType() {
            return EMPTY;
        }

        @Override
        public TrackElement getRightNewType() {
            return EMPTY;
        }
    },
    SQUARE_POINT(0, 0, 0, 0, true) {
        @Override
        public TrackElement getLeftNewType() {
            return SQUARE_POINT;
        }

        @Override
        public TrackElement getRightNewType() {
            return SQUARE_POINT;
        }
    },
    L_MIDDLE_MIDDLE(0, 0, 0, 0, true) {
        @Override
        public TrackElement getLeftNewType() {
            return L_MIDDLE_MIDDLE;
        }

        @Override
        public TrackElement getRightNewType() {
            return L_MIDDLE_MIDDLE;
        }
    },
    L_UP_LEFT(0, 2, -2, 0, true) {
        @Override
        public TrackElement getLeftNewType() {
            return L_DOWN_LEFT;
        }

        @Override
        public TrackElement getRightNewType() {
            return L_UP_RIGHT;
        }
    },
    L_MIDDLE_LEFT(-1, 1, 1, 1, true) {
        @Override
        public TrackElement getLeftNewType() {
            return L_DOWN_MIDDLE;
        }

        @Override
        public TrackElement getRightNewType() {
            return L_UP_MIDDLE;
        }
    },
    L_DOWN_LEFT(-2, 0, 0, 2, true) {
        @Override
        public TrackElement getLeftNewType() {
            return L_DOWN_RIGHT;
        }

        @Override
        public TrackElement getRightNewType() {
            return L_UP_LEFT;
        }
    },
    L_UP_MIDDLE(1, 1, 1, -1, true) {
        @Override
        public TrackElement getLeftNewType() {
            return L_MIDDLE_LEFT;
        }

        @Override
        public TrackElement getRightNewType() {
            return L_MIDDLE_RIGHT;
        }
    },
    L_DOWN_MIDDLE(-1, -1, -1, 1, true) {
        @Override
        public TrackElement getLeftNewType() {
            return L_MIDDLE_RIGHT;
        }

        @Override
        public TrackElement getRightNewType() {
            return L_MIDDLE_LEFT;
        }
    },
    L_UP_RIGHT(2, 0, 0, -2, true) {
        @Override
        public TrackElement getLeftNewType() {
            return L_UP_LEFT;
        }

        @Override
        public TrackElement getRightNewType() {
            return L_DOWN_RIGHT;
        }
    },
    L_MIDDLE_RIGHT(1, -1, -1, -1, true) {
        @Override
        public TrackElement getLeftNewType() {
            return L_UP_MIDDLE;
        }

        @Override
        public TrackElement getRightNewType() {
            return L_DOWN_MIDDLE;
        }
    },
    L_DOWN_RIGHT(0, -2, -2, 0, true) {
        @Override
        public TrackElement getLeftNewType() {
            return L_UP_RIGHT;
        }

        @Override
        public TrackElement getRightNewType() {
            return L_DOWN_LEFT;
        }
    };

    public final int rotateRightRowIndex, rotateRightColumnIndex, rotateLeftRowIndex, rotateLeftColumnIndex;
    public final boolean canMoveToTheSide;

    TrackElement(int rotateRightRowIndex, int rotateRightColumnIndex,
                 int rotateLeftRowIndex, int rotateLeftColumnIndex,
                 boolean canMoveToTheSide) {
        this.rotateRightRowIndex = rotateRightRowIndex;
        this.rotateRightColumnIndex = rotateRightColumnIndex;
        this.rotateLeftRowIndex = rotateLeftRowIndex;
        this.rotateLeftColumnIndex = rotateLeftColumnIndex;
        this.canMoveToTheSide = canMoveToTheSide;
    }


    public abstract TrackElement getLeftNewType();

    public abstract TrackElement getRightNewType();

}
