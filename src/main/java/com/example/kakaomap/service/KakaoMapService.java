package com.example.kakaomap.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class KakaoMapService {

    @Value("${kakao.rest-key}")
    private String kakaoRestKey;

    private final RestTemplate restTemplate;

    public KakaoMapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getRoute(
            double startLat, double startLng,
            double endLat, double endLng
    ) {
        String url =
                "https://apis-navi.kakaomobility.com/v1/directions" +
                        "?origin=" + startLng + "," + startLat +
                        "&destination=" + endLng + "," + endLat;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoRestKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );

        return response.getBody();
    }
}
