package org.bexterlab.tetrisbackend.exception;

public abstract class TetrisException extends RuntimeException {

    public final String errorMessage;

    public TetrisException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public TetrisException(String message, Throwable cause) {
        super(message, cause);
        this.errorMessage = message;
    }

    public TetrisException(Throwable cause) {
        super(cause);
        this.errorMessage = "EMPTY";
    }
}
