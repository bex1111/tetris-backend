package org.bexterlab.tetrisbackend.core.exception;

public class CanNotMoveException extends CoreException {

    public static final String CAN_NOT_MOVE_ELEMENT = "CAN_NOT_MOVE_ELEMENT";

    public CanNotMoveException(IndexOutOfBoundsException e) {
        super(CAN_NOT_MOVE_ELEMENT, e);
    }

    public CanNotMoveException() {
        super(CAN_NOT_MOVE_ELEMENT);
    }
}
