package org.bexterlab.tetrisbackend.core;

import java.util.List;

public interface UserStore {

    boolean hasGameWithUser(String username);

    boolean hasGameWithUserAndToken(String username, String token);

    List<String> findUsernames();

    void storePoint(String username, Long point);

    void addPlayerIntoScoreBoard(String username);
}
