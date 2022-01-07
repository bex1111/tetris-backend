package org.bexterlab.tetrisbackend.core.move;

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
    THREE_LONG_ELEMENT_MIDDLE_MIDDLE(0, 0, 0, 0, true) {
        @Override
        public TrackElement getLeftNewType() {
            return THREE_LONG_ELEMENT_MIDDLE_MIDDLE;
        }

        @Override
        public TrackElement getRightNewType() {
            return THREE_LONG_ELEMENT_MIDDLE_MIDDLE;
        }
    },
    THREE_LONG_ELEMENT_UP_LEFT(0, 2, 2, 0, true) {
        @Override
        public TrackElement getLeftNewType() {
            return THREE_LONG_ELEMENT_DOWN_LEFT;
        }

        @Override
        public TrackElement getRightNewType() {
            return THREE_LONG_ELEMENT_UP_RIGHT;
        }
    },
    THREE_LONG_ELEMENT_MIDDLE_LEFT(-1, 1, 1, 1, true) {
        @Override
        public TrackElement getLeftNewType() {
            return THREE_LONG_ELEMENT_DOWN_MIDDLE;
        }

        @Override
        public TrackElement getRightNewType() {
            return THREE_LONG_ELEMENT_UP_MIDDLE;
        }
    },
    THREE_LONG_ELEMENT_DOWN_LEFT(-2, 0, 0, 2, true) {
        @Override
        public TrackElement getLeftNewType() {
            return THREE_LONG_ELEMENT_DOWN_RIGHT;
        }

        @Override
        public TrackElement getRightNewType() {
            return THREE_LONG_ELEMENT_UP_LEFT;
        }
    },
    THREE_LONG_ELEMENT_UP_MIDDLE(1, 1, 1, -1, true) {
        @Override
        public TrackElement getLeftNewType() {
            return THREE_LONG_ELEMENT_MIDDLE_LEFT;
        }

        @Override
        public TrackElement getRightNewType() {
            return THREE_LONG_ELEMENT_MIDDLE_RIGHT;
        }
    },
    THREE_LONG_ELEMENT_DOWN_MIDDLE(-1, -1, -1, 1, true) {
        @Override
        public TrackElement getLeftNewType() {
            return THREE_LONG_ELEMENT_MIDDLE_RIGHT;
        }

        @Override
        public TrackElement getRightNewType() {
            return THREE_LONG_ELEMENT_MIDDLE_LEFT;
        }
    },
    THREE_LONG_ELEMENT_UP_RIGHT(2, 0, 0, -2, true) {
        @Override
        public TrackElement getLeftNewType() {
            return THREE_LONG_ELEMENT_UP_LEFT;
        }

        @Override
        public TrackElement getRightNewType() {
            return THREE_LONG_ELEMENT_DOWN_RIGHT;
        }
    },
    THREE_LONG_ELEMENT_MIDDLE_RIGHT(1, -1, -1, -1, true) {
        @Override
        public TrackElement getLeftNewType() {
            return THREE_LONG_ELEMENT_UP_MIDDLE;
        }

        @Override
        public TrackElement getRightNewType() {
            return THREE_LONG_ELEMENT_DOWN_MIDDLE;
        }
    },
    THREE_LONG_ELEMENT_DOWN_RIGHT(0, -2, -2, 0, true) {
        @Override
        public TrackElement getLeftNewType() {
            return THREE_LONG_ELEMENT_UP_RIGHT;
        }

        @Override
        public TrackElement getRightNewType() {
            return THREE_LONG_ELEMENT_DOWN_LEFT;
        }
    };

    public final int rotateRightRowIndex, rotateRightColumnIndex, rotateLeftRowIndex, rotateLeftColumnIndex;
    public final boolean isNotFix;

    TrackElement(int rotateRightRowIndex, int rotateRightColumnIndex,
                 int rotateLeftRowIndex, int rotateLeftColumnIndex,
                 boolean isNotFix) {
        this.rotateRightRowIndex = rotateRightRowIndex;
        this.rotateRightColumnIndex = rotateRightColumnIndex;
        this.rotateLeftRowIndex = rotateLeftRowIndex;
        this.rotateLeftColumnIndex = rotateLeftColumnIndex;
        this.isNotFix = isNotFix;
    }

    public abstract TrackElement getLeftNewType();

    public abstract TrackElement getRightNewType();

}
