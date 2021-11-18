package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.controller.StartGameInteractor;
import org.bexterlab.tetrisbackend.core.exception.InvalidUsernameException;
import org.bexterlab.tetrisbackend.core.exception.YouAlreadyHaveAGameException;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.TrackElement;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

import static org.bexterlab.tetrisbackend.entity.TrackElement.EMPTY;

@Service
public class StartGameInteractorImpl implements StartGameInteractor {

    public static final String USER_NAME_VALIDATOR_REGEXP = "^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$";
    private final GameStore gameStore;


    public StartGameInteractorImpl(GameStore gameStore) {
        this.gameStore = gameStore;
    }

    public String start(String username) {
        validateUserName(username);
        checkPlayHasAlreadyAGame(username);
        Game game = createNewGame(username);
        gameStore.createNewGame(game);
        return game.token();
    }

    private Game createNewGame(String username) {
        return new Game(username,
                UUID.randomUUID().toString(),
                createEmptyTrack(), new LinkedList<>());
    }

    private TrackElement[][] createEmptyTrack() {
        TrackElement[][] track = new TrackElement[24][20];
        for (TrackElement[] trackElements : track) {
            Arrays.fill(trackElements, EMPTY);
        }
        return track;
    }

    private void checkPlayHasAlreadyAGame(String username) {
        if (gameStore.hasGameWithUser(username)) {
            throw new YouAlreadyHaveAGameException();
        }
    }

    private void validateUserName(String username) {
        if (!username.matches(USER_NAME_VALIDATOR_REGEXP)) {
            throw new InvalidUsernameException();
        }
    }
}
