package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.entity.Movement;

public interface MovementStore {

    void addNew(String username, Movement movement);

    int count(String username);
}
