package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.controller.dto.PointsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ListPointsController {

    private final ListPointsInteractor listScoreInteractor;

    public ListPointsController(ListPointsInteractor listPointsInteractor) {
        this.listScoreInteractor = listPointsInteractor;
    }

    @GetMapping("/listPoints")
    public List<PointsDto> list() {
        return listScoreInteractor
                .list()
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(x -> new PointsDto().setPoints(x.getValue()).setUsername(x.getKey()))
                .collect(Collectors.toList());
    }
}
