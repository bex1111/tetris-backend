package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.AsyncGameHandler;

public class AsyncGameHandlerSpy extends AsyncGameHandler {

    public boolean isStartGameCalled;

    public AsyncGameHandlerSpy() {
        super(null, null, null, null, null, 1L);
    }

    @Override
    public void startGame() {
        isStartGameCalled = true;
    }
}
