package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;

import java.util.LinkedList;

public class TetrisStepFactoryElementDrawMock extends TetrisStepFactory {

    public LinkedList<TetrisElement> elementLinkedList;

    public TetrisStepFactoryElementDrawMock() {
        this.elementLinkedList = new LinkedList<>();
    }

    @Override
    public TetrisElement drawTetrisElement() {
        return elementLinkedList.poll();
    }
}
