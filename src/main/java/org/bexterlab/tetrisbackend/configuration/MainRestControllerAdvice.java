package org.bexterlab.tetrisbackend.configuration;

import org.bexterlab.tetrisbackend.core.Logger;
import org.bexterlab.tetrisbackend.exception.TetrisException;
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
        logger.error("Tetris exception occured: ", e);
        return e.errorMessage;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleOtherException(Throwable e) {
        logger.error("Unexpected exception: ", e);
        return "CALL_BARNA";
    }


}
