package org.openschool.metricsconsumer.kafka.listener;

import org.openschool.entity.Metric;
import org.openschool.metricsconsumer.service.MetricsEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MetricsListener {
    private final MetricsEntityService metricsEntityService;

    @KafkaListener(topics = "${app.kafka.kafkaMessageTopic}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "kafkaListenerOrderContainerFactory")
    public void listen(@Payload List<Metric> metrics,
                       @Header(value = KafkaHeaders.KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(value = KafkaHeaders.PARTITION, required = false) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        metricsEntityService.saveMetrics(metrics);
    }
}