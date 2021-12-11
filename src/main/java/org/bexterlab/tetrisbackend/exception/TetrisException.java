package org.bexterlab.tetrisbackend.exception;

public abstract class TetrisException extends RuntimeException {
    public TetrisException(String message) {
        super(message);
    }

    public TetrisException(String message, Throwable cause) {
        super(message, cause);
    }

    public TetrisException(Throwable cause) {
        super(cause);
    }
}
