package org.bexterlab.tetrisbackend.exception;

import org.bexterlab.tetrisbackend.commonmock.LoggerSpy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainRestControllerAdviceTest {

    private MainRestControllerAdvice mainRestControllerAdvice;
    private LoggerSpy loggerSpy;

    @BeforeEach
    void setUp() {
        loggerSpy = new LoggerSpy();
        mainRestControllerAdvice = new MainRestControllerAdvice(loggerSpy);
    }

    @Test
    void handleCoreExceptionTest() {
        String message = "test";
        TestTetrisException testTetrisException = new TestTetrisException(message);
        Assertions.assertEquals(message, mainRestControllerAdvice.handleCoreException(testTetrisException));
        Assertions.assertEquals("Tetris exception occurred: ", loggerSpy.object);
        Assertions.assertEquals(testTetrisException, loggerSpy.throwable);
    }

    @Test
    void handleOtherExceptionTest() {
        Throwable throwable = new Throwable();
        Assertions.assertEquals("CALL_BARNA", mainRestControllerAdvice.handleOtherException(throwable));
        Assertions.assertEquals("Unexpected exception occurred: ", loggerSpy.object);
        Assertions.assertEquals(throwable, loggerSpy.throwable);
    }
}