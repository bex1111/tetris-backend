package org.bexterlab.tetrisbackend.core;

public enum TrackElement {

    POINT(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return POINT;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    },
    EMPTY(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return EMPTY;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    },
    SQUARE_POINT(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return SQUARE_POINT;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    },
    L_MIDDLE_MIDDLE(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return L_MIDDLE_MIDDLE;
        }

        @Override
        public TrackElement getRightNewType() {
            return L_MIDDLE_MIDDLE;
        }
    },
    L_UP_LEFT(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return null;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    },
    L_MIDDLE_LEFT(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return null;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    },
    L_DOWN_LEFT(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return null;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    },
    L_UP_MIDDLE(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return null;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    },
    L_DOWN_MIDDLE(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return null;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    },
    L_UP_RIGH(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return null;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    },
    L_MIIDLE_RIGHT(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return null;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    },
    L_DOWN_RIGHT(0, 0, 0, 0) {
        @Override
        public TrackElement getLeftNewType() {
            return null;
        }

        @Override
        public TrackElement getRightNewType() {
            return null;
        }
    };

    public final int rotateRightRowIndex, rotateRightColumnIndex, rotateLeftRowIndex, rotateLeftColumnIndex;

    TrackElement(int rotateRowIndex, int rotateColumnIndex, int rotateLeftRowIndex, int rotateLeftColumnIndex) {
        this.rotateRightRowIndex = rotateRowIndex;
        this.rotateRightColumnIndex = rotateColumnIndex;
        this.rotateLeftRowIndex = rotateLeftRowIndex;
        this.rotateLeftColumnIndex = rotateLeftColumnIndex;
    }


    public abstract TrackElement getLeftNewType();

    public abstract TrackElement getRightNewType();

}
