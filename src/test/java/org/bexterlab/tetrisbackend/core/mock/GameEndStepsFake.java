package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.steps.GameEndSteps;

import java.util.List;

public class GameEndStepsFake extends GameEndSteps {

    public final List<String> steps;

    public GameEndStepsFake(List<String> steps) {
        super(null, null, 1);
        this.steps = steps;
    }

    @Override
    public void execute(String username) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
    }
}
