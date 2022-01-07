package org.bexterlab.tetrisbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    //FIXME test me
    @GetMapping("/health")
    public void health() {
    }
}
