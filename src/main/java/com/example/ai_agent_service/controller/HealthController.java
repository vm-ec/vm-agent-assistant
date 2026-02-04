package com.example.ai_agent_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    private final RestClient restClient;
    private final String wrapperServiceUrl;

    public HealthController(RestClient.Builder restClientBuilder,
                           @Value("${wrapper.service.url}") String wrapperServiceUrl) {
        this.restClient = restClientBuilder.build();
        this.wrapperServiceUrl = wrapperServiceUrl;
    }

    @GetMapping("/health/dependencies")
    public ResponseEntity<Map<String, Object>> checkDependencies() {
        Map<String, Object> status = new HashMap<>();
        
        try {
            restClient.get()
                    .uri(wrapperServiceUrl.replace("/ai/chat", "/health"))
                    .retrieve()
                    .toBodilessEntity();
            status.put("wrapperService", "UP");
        } catch (Exception e) {
            status.put("wrapperService", "DOWN - " + e.getMessage());
        }
        
        return ResponseEntity.ok(status);
    }
}