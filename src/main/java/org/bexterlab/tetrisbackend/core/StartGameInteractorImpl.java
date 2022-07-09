package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.configuration.GameConfiguration;
import org.bexterlab.tetrisbackend.controller.StartGameInteractor;
import org.bexterlab.tetrisbackend.core.exception.InvalidUsernameException;
import org.bexterlab.tetrisbackend.core.exception.MaxUserCountReachedException;
import org.bexterlab.tetrisbackend.core.exception.OutOfRoundException;
import org.bexterlab.tetrisbackend.core.exception.YouAlreadyHaveAGameException;
import org.bexterlab.tetrisbackend.core.gateway.GameStore;
import org.bexterlab.tetrisbackend.core.gateway.UserInformationStore;
import org.bexterlab.tetrisbackend.core.gateway.UserStore;
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
    private final AsyncGameRunnerInteractor asyncGameRunnerInteractor;
    private final TetrisStepFactory tetrisStepFactory;
    private final GameConfiguration gameConfiguration;
    private final UserInformationStore userInformationStore;

    public StartGameInteractorImpl(GameStore gameStore,
                                   UserStore userStore,
                                   AsyncGameRunnerInteractor asyncGameRunnerInteractor,
                                   TetrisStepFactory tetrisStepFactory,
                                   GameConfiguration gameConfiguration,
                                   UserInformationStore userInformationStore) {
        this.gameStore = gameStore;
        this.userStore = userStore;
        this.asyncGameRunnerInteractor = asyncGameRunnerInteractor;
        this.tetrisStepFactory = tetrisStepFactory;
        this.gameConfiguration = gameConfiguration;
        this.userInformationStore = userInformationStore;
    }

    public String start(String username) {
        checkUserLimitReached();
        validateUserName(username);
        checkPlayHasAlreadyAGame(username);
        checkPlayerPlayAllGames(username);
        Game game = createNewGame(username);
        gameStore.createNewGame(game);
        asyncGameRunnerInteractor.startGame();
        return game.getUser().getToken();
    }

    private Game createNewGame(String username) {
        return new Game()
                .setUser(new User()
                        .setUsername(username)
                        .setToken(UUID.randomUUID().toString())
                        .setPoints(0L))
                .setTrack(createEmptyTrack())
                .setMovementQueue(new ConcurrentLinkedQueue<>())
                .setTetrisElements(new TetrisElements()
                        .setCurrent(tetrisStepFactory.drawTetrisElement())
                        .setNext(tetrisStepFactory.drawTetrisElement()));
    }

    private TrackElement[][] createEmptyTrack() {
        TrackElement[][] track =
                new TrackElement[gameConfiguration.getTrackHeight()]
                        [gameConfiguration.getTrackWidth()];
        for (TrackElement[] trackElements : track) {
            Arrays.fill(trackElements, EMPTY);
        }
        return track;
    }

    private void checkPlayerPlayAllGames(String username) {
        if (userInformationStore.countUserRound(username) >= gameConfiguration.getRound()) {
            throw new OutOfRoundException();
        }
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
        if (userStore.countUser() >= gameConfiguration.getMaxUserCount()) {
            throw new MaxUserCountReachedException();
        }
    }
}
