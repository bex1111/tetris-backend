package org.bexterlab.tetrisbackend.core.exception;

import org.bexterlab.tetrisbackend.exception.TetrisException;

public abstract class CoreException extends TetrisException {

    protected CoreException(String errorMessage) {
        super(errorMessage);
    }

    protected CoreException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);

    }

    public CoreException(Throwable cause) {
        super(cause);
    }
}
