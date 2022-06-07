package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.entity.User;

import java.util.List;

public interface UserStore {

    boolean hasGameWithUser(String username);

    boolean hasGameWithUserAndToken(String username, String token);

    long countUser();

    List<String> findUsernames();

    void storePoint(String username, Long point);

    void addPlayerIntoScoreBoard(String username);

    List<User> findUsers();
}
