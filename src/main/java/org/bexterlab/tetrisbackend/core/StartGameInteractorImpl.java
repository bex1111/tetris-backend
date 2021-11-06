package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.controller.StartGameInteractor;
import org.bexterlab.tetrisbackend.core.exception.InvalidUsernameException;
import org.bexterlab.tetrisbackend.core.exception.YouAlreadyHaveAGameException;
import org.springframework.stereotype.Service;

@Service
public class StartGameInteractorImpl implements StartGameInteractor {

    public static final String USER_NAME_VALIDATOR_REGEXP = "^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$";
    private final GameStore gameStore;


    public StartGameInteractorImpl(GameStore gameStore) {
        this.gameStore = gameStore;
    }

    public String start(String username)
    {
        validateUserName(username);
        chackPlayHasAlreadyAGame(username);
        return gameStore.createNewGame(username);
    }

    private void chackPlayHasAlreadyAGame(String username) {
        if (gameStore.hasGameWithUser(username))
        {
            throw new YouAlreadyHaveAGameException();
        }
    }

    private void validateUserName(String username) {
        if (!username.matches(USER_NAME_VALIDATOR_REGEXP))
        {
            throw new InvalidUsernameException();
        }
    }
}
