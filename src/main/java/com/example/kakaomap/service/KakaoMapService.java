package com.example.kakaomap.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class KakaoMapService {

    private static final String KAKAO_REST_KEY =
            "4415c882c7c0fe64e567516b6bfadfb2";

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
        headers.set("Authorization", "KakaoAK " + KAKAO_REST_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

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
