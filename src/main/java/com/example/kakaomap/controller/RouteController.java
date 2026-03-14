package com.example.kakaomap.controller;

import com.example.kakaomap.dto.Point;
import com.example.kakaomap.service.KakaoMapService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final KakaoMapService kakaoMapService;
    private Point lastLocation = new Point();

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

    @PostMapping("/location")
    public String saveLocation(@RequestBody Point point) {
        lastLocation = point;
        return "ok";
    }

    @GetMapping("/location")
    public Point getLocation() {
        return lastLocation;
    }
}