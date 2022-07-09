package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.configuration.GameConfiguration;
import org.bexterlab.tetrisbackend.controller.StartGameInteractor;
import org.bexterlab.tetrisbackend.core.exception.InvalidUsernameException;
import org.bexterlab.tetrisbackend.core.exception.MaxUserCountReachedException;
import org.bexterlab.tetrisbackend.core.exception.OutOfRoundException;
import org.bexterlab.tetrisbackend.core.exception.YouAlreadyHaveAGameException;
import org.bexterlab.tetrisbackend.core.mock.*;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

class StartGameInteractorImplTest {

    public static final String USERNAME = "tetrisMaestro";
    private GameStoreFake gameStore;
    private UserStoreFake userStore;
    private StartGameInteractor startGameInteractor;
    private AsyncGameRunnerInteractorSpy asyncGameHandler;
    private UserInformationStoreFake userInformationStoreFake;
    private GameConfiguration gameConfiguration;
    private List<String> calledMethodName;

    @BeforeEach
    void setUp() {
        calledMethodName = new ArrayList<>();
        gameStore = new GameStoreFake();
        userStore = new UserStoreFake();
        asyncGameHandler = new AsyncGameRunnerInteractorSpy();
        userInformationStoreFake = new UserInformationStoreFake(calledMethodName);
        gameConfiguration = new GameConfiguration()
                .setMaxUserCount(30L)
                .setTrackHeight(10)
                .setTrackWidth(11)
                .setRound(3);
        startGameInteractor = new StartGameInteractorImpl(gameStore,
                userStore,
                asyncGameHandler,
                new TetrisStepFactoryFake(calledMethodName),
                gameConfiguration, userInformationStoreFake);
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
        Assertions.assertNotNull(token, gameStore.game.getUser().getToken());
        Assertions.assertDoesNotThrow(() -> UUID.fromString(token));
        Assertions.assertEquals("valid_user", gameStore.game.getUser().getUsername());
        Assertions.assertEquals(gameConfiguration.getTrackHeight(), gameStore.game.getTrack().length);
        Arrays.stream(gameStore.game.getTrack()).forEach(x -> Assertions.assertEquals(x.length, gameConfiguration.getTrackWidth()));
        Assertions.assertTrue(Arrays.stream(gameStore.game.getTrack())
                        .allMatch(x -> Arrays.stream(x)
                                .allMatch(y -> y == TrackElement.EMPTY)),
                Arrays.deepToString(gameStore.game.getTrack()));
        Assertions.assertTrue(asyncGameHandler.isStartGameCalled);
        Assertions.assertEquals(
                List.of("countUserRound",
                        "drawTetrisElement",
                        "drawTetrisElement"),
                calledMethodName);
    }

    @Test
    public void newPlayerCanStartGameWhenPlayerNumberIsUnderLimitTest() {
        userStore.userCount = 29;
        String token = startGameInteractor.start("valid_user");
        Assertions.assertNotNull(token);
    }

    @Test
    public void newPlayerCanNotStartGameWhenPlayerNumberEqualsLimitTest() {
        userStore.userCount = 30;
        Assertions.assertThrows(MaxUserCountReachedException.class, () -> startGameInteractor.start(USERNAME));
    }

    @Test
    public void newPlayerCanNotStartGameWhenPlayerNumberReachedMaxLimitTest() {
        userStore.userCount = 31;
        Assertions.assertThrows(MaxUserCountReachedException.class, () -> startGameInteractor.start(USERNAME));
    }

    @Test
    public void userHasTooManyRoundTest() {
        userInformationStoreFake.countUserRound = 3;
        Assertions.assertThrows(OutOfRoundException.class, () -> startGameInteractor.start(USERNAME));
        Assertions.assertEquals(USERNAME, userInformationStoreFake.countUserRoundUsername);
    }
}