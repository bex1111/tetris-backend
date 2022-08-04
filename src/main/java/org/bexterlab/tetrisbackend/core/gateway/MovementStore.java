package org.bexterlab.tetrisbackend.core.gateway;

import org.bexterlab.tetrisbackend.core.move.Movement;

public interface MovementStore {

    void addNewMovement(String username, Movement movement);

    int countMovement(String username);
}
