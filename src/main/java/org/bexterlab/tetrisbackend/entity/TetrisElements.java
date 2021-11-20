package org.bexterlab.tetrisbackend.entity;

import org.bexterlab.tetrisbackend.core.maintenance.NewElementSpawner;

public record TetrisElements(
        NewElementSpawner.TetrisElement current,
        NewElementSpawner.TetrisElement next) {
}
