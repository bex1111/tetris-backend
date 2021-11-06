package org.bexterlab.tetrisbackend.configuration;

import org.bexterlab.tetrisbackend.core.exception.CoreException;
import org.slf4j.Logger;
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

    @ExceptionHandler(CoreException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCoreException(CoreException e) {
        logger.error("Core exception occured: ", e);
        return e.errorMessage;
    }


}
