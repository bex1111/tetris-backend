package org.bexterlab.tetrisbackend.core.exception;

public abstract class CoreException extends RuntimeException {

    public final String errorMessage;

    protected CoreException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
