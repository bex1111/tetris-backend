package org.bexterlab.tetrisbackend.entity;

import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;

public record TetrisElements(
        TetrisElement current,
        TetrisElement next) {
}
