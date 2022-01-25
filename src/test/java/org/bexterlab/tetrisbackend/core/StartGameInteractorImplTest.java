package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.controller.StartGameInteractor;
import org.bexterlab.tetrisbackend.core.exception.InvalidUsernameException;
import org.bexterlab.tetrisbackend.core.exception.MaxUserCountReachedException;
import org.bexterlab.tetrisbackend.core.exception.YouAlreadyHaveAGameException;
import org.bexterlab.tetrisbackend.core.mock.AsyncGameRunnerInteractorSpy;
import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class StartGameInteractorImplTest {

    private final long maxUserLimit = 30;
    private GameStoreFake gameStore;
    private UserStoreFake userStore;
    private StartGameInteractor startGameInteractor;
    private AsyncGameRunnerInteractorSpy asyncGameHandler;

    @BeforeEach
    void setUp() {
        gameStore = new GameStoreFake();
        userStore = new UserStoreFake();
        asyncGameHandler = new AsyncGameRunnerInteractorSpy();
        startGameInteractor = new StartGameInteractorImpl(gameStore, userStore, asyncGameHandler, maxUserLimit);
    }

    @Test
    public void invalidUsernameTest() {
        Assertions.assertThrows(InvalidUsernameException.class, () -> startGameInteractor.start("%start_with_Special"));
        Assertions.assertThrows(InvalidUsernameException.class, () -> startGameInteractor.start("short"));
        Assertions.assertThrows(InvalidUsernameException.class, () -> startGameInteractor.start("greater_then_20_chara"));
        Assertions.assertThrows(InvalidUsernameException.class, () -> startGameInteractor.start("test._"));
        Assertions.assertThrows(InvalidUsernameException.class, () -> startGameInteractor.start("test_."));
    }

    @Test
    public void hasAGameTest() {
        userStore.hasGameWithUser = true;
        Assertions.assertThrows(YouAlreadyHaveAGameException.class, () -> startGameInteractor.start("validuser"));
        Assertions.assertThrows(YouAlreadyHaveAGameException.class, () -> startGameInteractor.start("ValidUser"));
        Assertions.assertThrows(YouAlreadyHaveAGameException.class, () -> startGameInteractor.start("ValidUser15"));
    }

    @Test
    public void successTest() {
        userStore.hasGameWithUser = false;
        String token = startGameInteractor.start("valid_user");
        Assertions.assertNotNull(token, gameStore.game.user().token());
        Assertions.assertEquals("valid_user", gameStore.game.user().username());
        Assertions.assertTrue(Arrays.stream(gameStore.game.track())
                        .allMatch(x -> Arrays.stream(x)
                                .allMatch(y -> y == TrackElement.EMPTY)),
                Arrays.deepToString(gameStore.game.track()));
        Assertions.assertTrue(asyncGameHandler.isStartGameCalled);
    }

    @Test
    public void newPlayerCanStartGameWhenPlayerNumberIsUnderLimit() {
        userStore.userCount = 29;
        String token = startGameInteractor.start("valid_user");
        Assertions.assertNotNull(token);
    }

    @Test
    public void newPlayerCanNotStartGameWhenPlayerNumberEqualsLimit() {
        userStore.userCount = 30;
        Assertions.assertThrows(MaxUserCountReachedException.class, () -> startGameInteractor.start("tetrisMaestro"));
    }

    @Test
    public void newPlayerCanNotStartGameWhenPlayerNumberReachedMaxLimit() {
        userStore.userCount = 31;
        Assertions.assertThrows(MaxUserCountReachedException.class, () -> startGameInteractor.start("tetrisMaestro"));
    }
}