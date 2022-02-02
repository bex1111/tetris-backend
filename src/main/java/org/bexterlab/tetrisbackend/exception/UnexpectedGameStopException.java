package org.bexterlab.tetrisbackend.exception;

public class UnexpectedGameStopException extends RuntimeException {
    public UnexpectedGameStopException(Throwable e) {
        super(e);
    }
}
