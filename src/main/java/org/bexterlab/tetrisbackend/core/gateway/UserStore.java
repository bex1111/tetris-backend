package org.bexterlab.tetrisbackend.core.gateway;

import java.util.List;

public interface UserStore {

    boolean hasGameWithUser(String username);

    boolean hasGameWithUserAndToken(String username, String token);

    long countUser();

    List<String> findUsernames();

    void storePoint(String username, Long point);

    long findPoint(String username);
}
