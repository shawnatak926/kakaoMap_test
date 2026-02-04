package com.example.kakaomap.controller;

import com.example.kakaomap.service.KakaoMapService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final KakaoMapService kakaoMapService;

    public RouteController(KakaoMapService kakaoMapService) {
        this.kakaoMapService = kakaoMapService;
    }

    @GetMapping("/route")
    public Map<String, Object> route(
            @RequestParam double startLat,
            @RequestParam double startLng,
            @RequestParam double endLat,
            @RequestParam double endLng
    ) {
        return kakaoMapService.getRoute(startLat, startLng, endLat, endLng);
    }
}
