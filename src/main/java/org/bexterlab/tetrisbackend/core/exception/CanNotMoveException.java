package org.bexterlab.tetrisbackend.core.exception;

public class CannotMoveException extends CoreException {

    public static final String CAN_NOT_MOVE_ELEMENT = "CAN_NOT_MOVE_ELEMENT";

    public CannotMoveException(IndexOutOfBoundsException e) {
        super(CAN_NOT_MOVE_ELEMENT, e);
    }

    public CannotMoveException() {
        super(CAN_NOT_MOVE_ELEMENT);
    }
}
