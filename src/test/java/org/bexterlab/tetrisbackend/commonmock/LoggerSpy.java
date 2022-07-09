package org.bexterlab.tetrisbackend.commonmock;

import org.bexterlab.tetrisbackend.core.gateway.Logger;

public class LoggerSpy implements Logger {

    public Object object;
    public Throwable throwable;

    @Override
    public void debug(Object info, Throwable throwable) {
        this.object = info;
        this.throwable = throwable;
    }

    @Override
    public void info(Object info) {
        this.object = info;
    }

    @Override
    public void error(Object info) {
        this.object = info;
    }

    @Override
    public void error(Object info, Throwable throwable) {
        this.object = info;
        this.throwable = throwable;
    }
}
