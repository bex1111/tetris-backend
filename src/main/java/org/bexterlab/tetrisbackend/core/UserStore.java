package org.bexterlab.tetrisbackend.core;

public interface UserStore {

    boolean hasGameWithUser(String username);

    boolean hasGameWithUserAndToken(String username, String token);
}
