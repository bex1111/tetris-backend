package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.steps.BaseSteps;

import java.util.List;

public class BaseStepsFake extends BaseSteps {
    public final List<String> steps;

    public BaseStepsFake(List<String> steps) {
        super(null, null);
        this.steps = steps;
    }

    @Override
    public void execute(String username) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
    }
}
