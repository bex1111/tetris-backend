package org.bexterlab.tetrisbackend.entity;

import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;


public class TetrisElements {
    private TetrisElement current;
    private TetrisElement next;

    public TetrisElement getCurrent() {
        return current;
    }

    public TetrisElements setCurrent(TetrisElement current) {
        this.current = current;
        return this;
    }

    public TetrisElement getNext() {
        return next;
    }

    public TetrisElements setNext(TetrisElement next) {
        this.next = next;
        return this;
    }
}
