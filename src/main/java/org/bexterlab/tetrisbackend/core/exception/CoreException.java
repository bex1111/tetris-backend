package org.bexterlab.tetrisbackend.core.exception;

public abstract class CoreException extends RuntimeException {

    public final String errorMessage;

    protected CoreException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    protected CoreException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }
}
