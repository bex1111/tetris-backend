package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.entity.Movement;

public interface ControlInteractor {
    
    void addMovement(String username, String token, Movement movement);
}
