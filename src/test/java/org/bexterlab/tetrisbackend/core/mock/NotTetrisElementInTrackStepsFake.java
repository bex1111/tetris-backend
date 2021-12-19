package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.steps.NotTetrisElementInTrackSteps;

import java.util.List;

public class NotTetrisElementInTrackStepsFake extends NotTetrisElementInTrackSteps {
    public final List<String> steps;

    public NotTetrisElementInTrackStepsFake(List<String> steps) {
        super(null, null, null);
        this.steps = steps;
    }

    @Override
    public void execute(String username) {
        steps.add(this.getClass().getSimpleName() + "|" + new Object() {
        }.getClass().getEnclosingMethod().getName());
    }
}
