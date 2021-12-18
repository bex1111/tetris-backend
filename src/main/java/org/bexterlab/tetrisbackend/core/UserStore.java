package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.User;

import java.util.List;

public interface UserStore {

    boolean hasGameWithUser(String username);

    boolean hasGameWithUserAndToken(String username, String token);

    List<String> findUsernames();

    void storePoint(Game game, Long point);

    void storePoint(String username, Long point);

    void addPlayerIntoScoreBoard(User user);

    void addPlayerIntoScoreBoard(String username);
}
