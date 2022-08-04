package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.configuration.MainConfiguration;
import org.bexterlab.tetrisbackend.core.gateway.Logger;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.mock.TetrisStepFactoryElementDrawMock;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.core.steps.BaseSteps;
import org.bexterlab.tetrisbackend.core.steps.GameEndSteps;
import org.bexterlab.tetrisbackend.core.steps.NotTetrisElementInTrackSteps;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.TetrisElements;
import org.bexterlab.tetrisbackend.entity.User;
import org.bexterlab.tetrisbackend.gateway.store.GameStoreImpl;
import org.bexterlab.tetrisbackend.gateway.store.ScoreBoardStoreImpl;
import org.bexterlab.tetrisbackend.gateway.store.UserInformationStoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.POINT;

class GameIntercatorIntTest {

    private static final String USERNAME = "int_test";
    private GameStoreImpl gameStore;
    private GameIntercator gameIntercator;
    private Logger logger;
    private TetrisStepFactoryElementDrawMock tetrisStepFactoryElementDrawMock;

    @BeforeEach
    void setUp() {
        MainConfiguration mainConfiguration = new MainConfiguration();
        logger = mainConfiguration.logger(mainConfiguration.objectMapper());

        gameStore = new GameStoreImpl(new CopyOnWriteArrayList<>());
        ScoreBoardStoreImpl scoreBoardStore = new ScoreBoardStoreImpl(new ConcurrentHashMap<>());
        UserInformationStoreImpl userInformationStore = new UserInformationStoreImpl(new ConcurrentHashMap<>());
        tetrisStepFactoryElementDrawMock = new TetrisStepFactoryElementDrawMock();

        GameEndSteps gameEndSteps = new GameEndSteps(
                gameStore, gameStore, 3,
                5, scoreBoardStore,
                userInformationStore, logger);
        NotTetrisElementInTrackSteps notTetrisElementInTrackSteps = new NotTetrisElementInTrackSteps(
                tetrisStepFactoryElementDrawMock,
                gameStore, gameStore);
        BaseSteps baseSteps = new BaseSteps(tetrisStepFactoryElementDrawMock, gameStore);
        gameIntercator = new GameIntercator(gameStore,
                gameEndSteps,
                notTetrisElementInTrackSteps,
                baseSteps,
                logger);
    }

    @Test
    void stuckIfMovementCantDoFixTest() {
        tetrisStepFactoryElementDrawMock.elementLinkedList.addAll(
                Collections.nCopies(25, TetrisElement.SQUARE));
        gameStore.createNewGame(createNewGame(createEmptyTrack()));
        addMovementNTimes(5, Movement.MOVE_RIGHT);
        callMaintenanceNTimes(12);
        addMovementNTimes(2, Movement.MOVE_RIGHT);
        callMaintenanceNTimes(11);
        callMaintenanceNTimes(11);
        addMovementNTimes(2, Movement.MOVE_LEFT);
        callMaintenanceNTimes(11);

        addMovementNTimes(4, Movement.MOVE_RIGHT);
        callMaintenanceNTimes(9);
        addMovementNTimes(2, Movement.MOVE_RIGHT);
        callMaintenanceNTimes(9);
        callMaintenanceNTimes(9);
        addMovementNTimes(2, Movement.MOVE_LEFT);
        callMaintenanceNTimes(9);

        addMovementNTimes(11, Movement.MOVE_LEFT);
        callMaintenanceNTimes(11);
        addMovementNTimes(11, Movement.MOVE_LEFT);
        callMaintenanceNTimes(10);

        Assertions.assertEquals(
                "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "          |\n" +
                        "EEPPPPPPPP|\n" +
                        "EEPPPPPPPP|\n",
                createTrackString());
    }

    @Test
    void leitateFixTest() {
        tetrisStepFactoryElementDrawMock.elementLinkedList.addAll(
                Collections.nCopies(25, TetrisElement.SQUARE));
        gameStore.createNewGame(createNewGame(createLevitateTrack()));
        callMaintenanceNTimes(1);
        callMaintenanceNTimes(1);
        callMaintenanceNTimes(1);
        callMaintenanceNTimes(1);
        callMaintenanceNTimes(1);

        Assertions.assertEquals(
                "       |\n" +
                        "       |\n" +
                        "       |\n" +
                        "       |\n" +
                        "  EE   |\n" +
                        "  EE   |\n" +
                        "       |\n" +
                        "       |\n" +
                        "       |\n" +
                        "       |\n" +
                        "PPPP   |\n",
                createTrackString());

    }

    private void callMaintenanceNTimes(int n) {
        for (int i = 0; i < n; i++) {
            gameIntercator.maintenanceTracks();
        }
        logger.info(createTrackString());
    }

    private void addMovementNTimes(int n, Movement movement) {
        for (int i = 0; i < n; i++) {
            gameStore.addNewMovement(USERNAME, movement);
        }
    }

    private String createTrackString() {
        StringBuilder finalText = new StringBuilder();
        for (TrackElement[] row : gameStore.findTrackByUser(USERNAME)) {
            for (TrackElement column : row) {
                switch (column) {
                    case EMPTY:
                        finalText.append(" ");
                        break;
                    case POINT:
                        finalText.append("P");
                        break;
                    default:
                        finalText.append("E");
                        break;
                }
            }
            finalText.append("|\n");
        }
        return finalText.toString();
    }

    private Game createNewGame(TrackElement[][] track) {
        return new Game()
                .setStartTime(LocalDateTime.now())
                .setUser(new User()
                        .setUsername(USERNAME)
                        .setToken(UUID.randomUUID().toString())
                        .setPoints(0L))
                .setTrack(track)
                .setMovementQueue(new ConcurrentLinkedQueue<>())
                .setTetrisElements(new TetrisElements()
                        .setCurrent(tetrisStepFactoryElementDrawMock.drawTetrisElement())
                        .setNext(tetrisStepFactoryElementDrawMock.drawTetrisElement()));
    }

    private TrackElement[][] createLevitateTrack() {
        return new TrackElement[][]{
                new TrackElement[]{
                        EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY
                },
                new TrackElement[]{
                        EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY
                },
                new TrackElement[]{
                        EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY
                },
                new TrackElement[]{
                        EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY
                },
                new TrackElement[]{
                        EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY
                },
                new TrackElement[]{
                        EMPTY, EMPTY, EMPTY, EMPTY, POINT, EMPTY, POINT
                },
                new TrackElement[]{
                        EMPTY, EMPTY, EMPTY, EMPTY, POINT, POINT, POINT
                },
                new TrackElement[]{
                        POINT, POINT, POINT, POINT, POINT, POINT, POINT
                },
                new TrackElement[]{
                        POINT, POINT, POINT, POINT, EMPTY, EMPTY, EMPTY
                },
                new TrackElement[]{
                        POINT, POINT, POINT, POINT, EMPTY, EMPTY, EMPTY
                },
                new TrackElement[]{
                        POINT, POINT, POINT, POINT, EMPTY, POINT, EMPTY
                },
        };
    }

    private TrackElement[][] createEmptyTrack() {
        TrackElement[][] track =
                new TrackElement[12][10];
        for (TrackElement[] trackElements : track) {
            Arrays.fill(trackElements, EMPTY);
        }
        return track;
    }

}