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
import org.bexterlab.tetrisbackend.core.exception.MaxUserCountReachedException;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;

public class StartGameInteractorImpl implements StartGameInteractor {

    public static final String USER_NAME_VALIDATOR_REGEXP = "^[\\w]{6,20}$";
    private final GameStore gameStore;
    private final UserStore userStore;
    private final AsyncGameRunnerInteractor asyncGameRunnerInteractor;
    private final long maxUserCount;

    public StartGameInteractorImpl(GameStore gameStore,
                                   UserStore userStore,
                                   AsyncGameRunnerInteractor asyncGameRunnerInteractor,
                                   long maxUserCount) {
        this.gameStore = gameStore;
        this.userStore = userStore;
        this.asyncGameRunnerInteractor = asyncGameRunnerInteractor;
        this.maxUserCount = maxUserCount;
    }

    public String start(String username) {
        checkUserLimitReached();
        validateUserName(username);
        checkPlayHasAlreadyAGame(username);
        Game game = createNewGame(username);
        gameStore.createNewGame(game);
        asyncGameRunnerInteractor.startGame();
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
        TrackElement[][] track = new TrackElement[24][10];
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

    private void checkUserLimitReached() {
        if (userStore.getUserCount() >= maxUserCount) {
            throw new MaxUserCountReachedException();
        }
    }
}
