package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.controller.StartGameInteractor;
import org.bexterlab.tetrisbackend.core.exception.InvalidUsernameException;
import org.bexterlab.tetrisbackend.core.exception.YouAlreadyHaveAGameException;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElementLottery;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.TetrisElements;
import org.bexterlab.tetrisbackend.entity.User;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;


public class StartGameInteractorImpl implements StartGameInteractor {

    public static final String USER_NAME_VALIDATOR_REGEXP = "^[\\w]{6,20}$";
    private final GameStore gameStore;
    private final UserStore userStore;
    private final AsyncGameHandler asyncGameHandler;


    public StartGameInteractorImpl(GameStore gameStore,
                                   UserStore userStore,
                                   AsyncGameHandler asyncGameHandler) {
        this.gameStore = gameStore;
        this.userStore = userStore;
        this.asyncGameHandler = asyncGameHandler;
    }

    public String start(String username) {
        validateUserName(username);
        checkPlayHasAlreadyAGame(username);
        Game game = createNewGame(username);
        gameStore.createNewGame(game);
        asyncGameHandler.startGame();
        return game.user().token();
    }

    private Game createNewGame(String username) {
        return new Game(new User(username,
                UUID.randomUUID().toString(),
                0L),
                createEmptyTrack(), new ConcurrentLinkedQueue<>(),
                new TetrisElements(new TetrisElementLottery().draw(), new TetrisElementLottery().draw()));
    }

    private TrackElement[][] createEmptyTrack() {
        TrackElement[][] track = new TrackElement[24][20];
        for (TrackElement[] trackElements : track) {
            Arrays.fill(trackElements, EMPTY);
        }
        return track;
    }

    private void checkPlayHasAlreadyAGame(String username) {
        if (userStore.hasGameWithUser(username)) {
            throw new YouAlreadyHaveAGameException();
        }
    }

    private void validateUserName(String username) {
        if (!username.matches(USER_NAME_VALIDATOR_REGEXP)) {
            throw new InvalidUsernameException();
        }
    }
}
