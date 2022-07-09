package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.gateway.MovementStore;
import org.bexterlab.tetrisbackend.core.move.Movement;

import java.util.ArrayList;
import java.util.List;

public class MovementStoreFake implements MovementStore {

    public int movementCount = 0;
    public String username;
    public Movement movement;
    private final List<String> callMethodName;

    public MovementStoreFake(List<String> callMethodName) {
        this.callMethodName = callMethodName;
    }

    public MovementStoreFake() {
        this.callMethodName = new ArrayList<>();
    }

    @Override
    public void addNew(String username, Movement movement) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        this.username = username;
        this.movement = movement;
    }

    @Override
    public int countMovement(String username) {
        callMethodName.add(new Object() {
        }.getClass().getEnclosingMethod().getName());
        return movementCount;
    }
}
