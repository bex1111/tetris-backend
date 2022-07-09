package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.steps.GameEndSteps;

import java.util.List;

import static java.util.Objects.nonNull;

public class GameEndStepsFake extends GameEndSteps {

    public final List<String> steps;
    public RuntimeException runtimeException;

    public GameEndStepsFake(List<String> steps) {
        super(null, null, 1, null, null);
        this.steps = steps;
    }

    @Override
    public void execute(String username) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
        if (nonNull(runtimeException)) {
            throw runtimeException;
        }
    }
}
