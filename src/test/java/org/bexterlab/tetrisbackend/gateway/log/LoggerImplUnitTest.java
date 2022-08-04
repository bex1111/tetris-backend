package org.bexterlab.tetrisbackend.gateway.log;

import com.fasterxml.jackson.core.JsonParseException;
import org.bexterlab.tetrisbackend.commonmock.ObjectMapperFake;
import org.bexterlab.tetrisbackend.controller.dto.StartGameDto;
import org.bexterlab.tetrisbackend.gateway.log.mock.LoggerSpy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class LoggerImplUnitTest {

    private LoggerImpl logger;
    private LoggerSpy loggerSpy;
    private ObjectMapperFake objectMapperFake;

    @BeforeEach
    void setUp() {
        loggerSpy = new LoggerSpy();
        objectMapperFake = new ObjectMapperFake();
        logger = new LoggerImpl(loggerSpy, objectMapperFake);
    }

    @Test
    void logDebugString() {
        String logString = "test";
        RuntimeException runtimeException = new RuntimeException();
        logger.debug(logString, runtimeException);
        Assertions.assertEquals(logString, loggerSpy.text);
    }

    @Test
    void logDebugObject() {
        String logString = "{\"username\":\"test\"}";
        RuntimeException runtimeException = new RuntimeException();
        logger.debug(new StartGameDto().setUsername("test"), runtimeException);
        Assertions.assertEquals(logString, loggerSpy.text);
    }

    @Test
    void logDebugList() {
        String logString = "[{\"username\":\"test\"}]";
        RuntimeException runtimeException = new RuntimeException();
        logger.debug(List.of(new StartGameDto().setUsername("test")), runtimeException);
        Assertions.assertEquals(logString, loggerSpy.text);
    }

    @Test
    void logInfoString() {
        String logString = "test";
        logger.info(logString);
        Assertions.assertEquals(logString, loggerSpy.text);
    }

    @Test
    void logInfoObject() {
        String logString = "{\"username\":\"test\"}";
        logger.info(new StartGameDto().setUsername("test"));
        Assertions.assertEquals(logString, loggerSpy.text);
    }

    @Test
    void logInfoList() {
        String logString = "[{\"username\":\"test\"}]";
        logger.info(List.of(new StartGameDto().setUsername("test")));
        Assertions.assertEquals(logString, loggerSpy.text);
    }

    @Test
    void logErrorString() {
        String logString = "test";
        logger.error(logString);
        Assertions.assertEquals(logString, loggerSpy.text);
    }

    @Test
    void logErrorObject() {
        String logString = "{\"username\":\"test\"}";
        logger.error(new StartGameDto().setUsername("test"));
        Assertions.assertEquals(logString, loggerSpy.text);
    }

    @Test
    void logErrorList() {
        String logString = "[{\"username\":\"test\"}]";
        logger.error(List.of(new StartGameDto().setUsername("test")));
        Assertions.assertEquals(logString, loggerSpy.text);
    }

    @Test
    void logErrorStringWithException() {
        String logString = "test";
        RuntimeException runtimeException = new RuntimeException();
        logger.error(logString, runtimeException);
        Assertions.assertEquals(logString, loggerSpy.text);
        Assertions.assertEquals(runtimeException, loggerSpy.throwable);
    }

    @Test
    void logErrorObjectWithException() {
        String logString = "{\"username\":\"test\"}";
        RuntimeException runtimeException = new RuntimeException();
        logger.error(new StartGameDto().setUsername("test"), runtimeException);
        Assertions.assertEquals(logString, loggerSpy.text);
        Assertions.assertEquals(runtimeException, loggerSpy.throwable);
    }

    @Test
    void logErrorListWithException() {
        String logString = "[{\"username\":\"test\"}]";
        RuntimeException runtimeException = new RuntimeException();
        logger.error(List.of(new StartGameDto().setUsername("test")), runtimeException);
        Assertions.assertEquals(logString, loggerSpy.text);
        Assertions.assertEquals(runtimeException, loggerSpy.throwable);
    }

    @Test
    void exceptionTest() {
        objectMapperFake.jsonParseException = new JsonParseException(null, "hupsz");
        Assertions.assertThrows(RuntimeException.class, () -> logger.info(new Object()));
        loggerSpy.text = "Exception occurred while generate log message to string.";
        Assertions.assertEquals(objectMapperFake.jsonParseException, loggerSpy.throwable);
    }
}