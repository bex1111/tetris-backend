package org.bexterlab.tetrisbackend.core.exception;

import org.bexterlab.tetrisbackend.exception.TetrisException;

public class TooManyMovementException extends TetrisException {
    public TooManyMovementException() {
        super("TOO_MANY_MOVEMENT");
    }
}
