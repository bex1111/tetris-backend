package org.bexterlab.tetrisbackend.exception;

import org.bexterlab.tetrisbackend.core.gateway.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainRestControllerAdvice {

    private final Logger logger;

    public MainRestControllerAdvice(Logger logger) {
        this.logger = logger;
    }

    @ExceptionHandler(TetrisException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCoreException(TetrisException e) {
        logger.error("Tetris exception occurred: ", e);
        return e.errorMessage;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleOtherException(Throwable e) {
        logger.error("Unexpected exception occurred: ", e);
        return String.format("CALL_BARNA_FOR_MORE_DETAILS|%s", e.getMessage());
    }
}
