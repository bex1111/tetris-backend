package org.bexterlab.tetrisbackend.core.exception;

public class CanNotRotateException extends CoreException {
    public CanNotRotateException(IndexOutOfBoundsException e) {
        super("CAN_NOT_ROTATE_ELEMENT", e);
    }
}
