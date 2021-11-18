package org.bexterlab.tetrisbackend.core.exception;

public class CanNotRotateException extends CoreException {

    public static final String CAN_NOT_ROTATE_ELEMENT = "CAN_NOT_ROTATE_ELEMENT";

    public CanNotRotateException(IndexOutOfBoundsException e) {
        super(CAN_NOT_ROTATE_ELEMENT, e);
    }

    public CanNotRotateException() {
        super(CAN_NOT_ROTATE_ELEMENT);
    }
}
