package org.bexterlab.tetrisbackend.controller;

import org.springframework.scheduling.annotation.Scheduled;

public class TrackScheduler {

    private final TrackInteractor trackInteractor;

    public TrackScheduler(TrackInteractor trackInteractor) {
        this.trackInteractor = trackInteractor;
    }

    @Scheduled(fixedRate = 200)
    public void scheduled() {
        trackInteractor.move();

    }

}
