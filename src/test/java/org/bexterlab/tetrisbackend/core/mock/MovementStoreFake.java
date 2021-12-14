package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.MovementStore;
import org.bexterlab.tetrisbackend.entity.Movement;

public class MovementStoreFake implements MovementStore {

    public int movementCount = 0;
    public String username;
    public Movement movement;

    @Override
    public void addNew(String username, Movement movement) {
        this.username = username;
        this.movement = movement;
    }

    @Override
    public int count(String username) {
        return movementCount;
    }
}
