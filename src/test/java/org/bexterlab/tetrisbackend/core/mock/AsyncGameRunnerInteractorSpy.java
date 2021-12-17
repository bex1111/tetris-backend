package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.AsyncGameRunnerInteractor;

public class AsyncGameRunnerInteractorSpy extends AsyncGameRunnerInteractor {

    public boolean isStartGameCalled;

    public AsyncGameRunnerInteractorSpy() {
        super(null, null, null, null, null, null);
    }

    @Override
    public void startGame() {
        isStartGameCalled = true;
    }
}
