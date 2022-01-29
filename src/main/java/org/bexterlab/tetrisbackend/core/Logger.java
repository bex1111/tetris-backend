package org.bexterlab.tetrisbackend.core;

public interface Logger {

    void debug(Object info, Throwable throwable);

    void info(Object info);

    void error(Object info);

    void error(Object info, Throwable throwable);

}
