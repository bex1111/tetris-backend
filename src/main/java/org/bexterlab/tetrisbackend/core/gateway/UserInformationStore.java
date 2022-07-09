package org.bexterlab.tetrisbackend.core.gateway;

public interface UserInformationStore {
    int countUserRound(String username);

    void saveRound(String username);
}
