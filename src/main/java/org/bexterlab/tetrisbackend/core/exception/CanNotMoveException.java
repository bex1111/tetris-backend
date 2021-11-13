package org.bexterlab.tetrisbackend.core.exception;

public class CanNotMoveException extends CoreException {
    public CanNotMoveException(IndexOutOfBoundsException e) {
        super("CAN_NOT_ROTATE_ELEMENT", e);
    }
}
