package org.openschool.metricsproducer.controller;

import org.openschool.metricsproducer.service.PostMetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/send")
public class MetricsProducerController {
    private final PostMetricsService metricsProducerService;

    @PostMapping("/metrics")
    public ResponseEntity<String> sendMetrics() {
        metricsProducerService.sendMetrics();
        return ResponseEntity.ok("Send metrics");
    }
}
