package org.bexterlab.tetrisbackend.gateway.socket;

import com.fasterxml.jackson.core.JsonProcessingException;

public class CannotGenerateSocketMessageException extends RuntimeException {

    public CannotGenerateSocketMessageException(JsonProcessingException e) {
        super(e);
    }
}
