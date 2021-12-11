package org.bexterlab.tetrisbackend.core.exception;

import org.bexterlab.tetrisbackend.exception.TetrisException;

public abstract class CoreException extends TetrisException {

    public final String errorMessage;

    protected CoreException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    protected CoreException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }

    public CoreException(Throwable cause) {
        super(cause);
        this.errorMessage = "Empty";
    }
}
