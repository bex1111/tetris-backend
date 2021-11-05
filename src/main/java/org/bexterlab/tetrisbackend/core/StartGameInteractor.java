package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.exception.InvalidUsernameException;
import org.bexterlab.tetrisbackend.core.exception.YouAlreadyHaveAGameException;
import org.springframework.stereotype.Service;

@Service
public class StartGameInteractor {

    private final GameStore gameStore;


    public StartGameInteractor(GameStore gameStore) {
        this.gameStore = gameStore;
    }

    public String start(String username)
    {
        if (!username.matches("^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$"))
        {
            throw new InvalidUsernameException();
        }
        if (gameStore.hasGameWithUser(username))
        {
            throw new YouAlreadyHaveAGameException();
        }
        return gameStore.createNewGame(username);
    }
}
