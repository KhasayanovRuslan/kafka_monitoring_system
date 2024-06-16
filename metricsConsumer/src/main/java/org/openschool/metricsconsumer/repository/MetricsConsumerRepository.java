package org.openschool.metricsconsumer.repository;

import org.openschool.metricsconsumer.entity.MetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsConsumerRepository extends JpaRepository<MetricEntity, Integer> {

    MetricEntity findById(int id);

    MetricEntity findByName(String name);

    boolean existsByName(String name);
}
