package org.bexterlab.tetrisbackend.core.gateway;

import org.bexterlab.tetrisbackend.core.move.Movement;

public interface MovementStore {

    void addNew(String username, Movement movement);

    int countMovement(String username);
}
