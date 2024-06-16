package org.openschool.metricsconsumer.service;

import org.openschool.entity.Metric;
import org.openschool.metricsconsumer.entity.MetricEntity;
import org.openschool.metricsconsumer.entity.MetricNotExistException;
import org.openschool.metricsconsumer.repository.MetricsConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsEntityService {
    private final MetricsConsumerRepository metricsConsumerRepository;

    public MetricEntity getMetricById(int id) {
        MetricEntity metricEntity = metricsConsumerRepository.findById(id);

        if (metricEntity == null) {
            log.info("Metric with id {} doesn't exist!", id);
            throw new MetricNotExistException("Metric with this id doesn't exist!");
        } else {
            log.info("Metric fetched successfully: {}", metricEntity);
            return metricEntity;
        }
    }

    public List<MetricEntity> getAllMetrics() {
        List<MetricEntity> metrics = metricsConsumerRepository.findAll();
        log.info("Get all metrics, total count: {}", metrics.size());

        return metrics;
    }

    public void saveMetrics(List<Metric> metrics) {
        metrics.forEach(metric -> {
            MetricEntity metricEntity = metricsConsumerRepository.findByName(metric.getName());

            String name = metric.getName();
            String type = metric.getType();
            String description = metric.getDescription();
            double value = metric.getValue();
            long timestamp = metric.getTimestamp();

            if(metricEntity == null) {
                metricsConsumerRepository.save(new MetricEntity(name, type, description, value, timestamp));
            } else {
                metricEntity.setName(name);
                metricEntity.setType(type);
                metricEntity.setDescription(description);
                metricEntity.setValue(value);
                metricEntity.setTimestamp(timestamp);

                metricsConsumerRepository.save(metricEntity);
                log.info("Metric saved successfully: {}", name);
            }
        });
    }
}
