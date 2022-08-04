package org.bexterlab.tetrisbackend.core.exception;

public class CannotRotateException extends CoreException {

    public static final String CAN_NOT_ROTATE_ELEMENT = "CAN_NOT_ROTATE_ELEMENT";

    public CannotRotateException(IndexOutOfBoundsException e) {
        super(CAN_NOT_ROTATE_ELEMENT, e);
    }

    public CannotRotateException() {
        super(CAN_NOT_ROTATE_ELEMENT);
    }
}
