package org.openschool.metricsconsumer.controller;

import org.openschool.metricsconsumer.entity.MetricEntity;
import org.openschool.metricsconsumer.service.MetricsEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/get")
public class MetricsConsumerController {
    private final MetricsEntityService metricsEntityService;

    @GetMapping("/metrics/{id}")
    public ResponseEntity<MetricEntity> getById(@PathVariable int id) {
        MetricEntity metric = metricsEntityService.getMetricById(id);

        return ResponseEntity.ok(metric);
    }

    @GetMapping("/metrics")
    public ResponseEntity<List<MetricEntity>> getAllMetrics() {
        List<MetricEntity> metrics = metricsEntityService.getAllMetrics();

        return ResponseEntity.ok(metrics);
    }
}
