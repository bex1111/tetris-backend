package org.bexterlab.tetrisbackend.gateway.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bexterlab.tetrisbackend.core.Logger;

import java.io.IOException;

public class LoggerImpl implements Logger {

    private final org.slf4j.Logger logger;
    private final ObjectMapper objectMapper;

    public LoggerImpl(org.slf4j.Logger logger, ObjectMapper objectMapper) {
        this.logger = logger;
        this.objectMapper = objectMapper;
    }

    @Override
    public void debug(Object info, Throwable throwable) {
        logger.debug(objectToString(info), throwable);
    }

    @Override
    public void info(Object info) {
        logger.info(objectToString(info));
    }

    @Override
    public void error(Object info) {
        logger.error(objectToString(info));
    }

    @Override
    public void error(Object info, Throwable throwable) {
        logger.error(objectToString(info), throwable);
    }


    private String objectToString(Object info) {
        if (info instanceof String) {
            return info.toString();
        }
        return convertObjectToString(info);
    }

    private String convertObjectToString(Object info) {
        try {
            return objectMapper.writeValueAsString(info);
        } catch (IOException e) {
            logger.error("Exception occurred while generate log message to string.", e);
            throw new RuntimeException(e);
        }
    }
}
