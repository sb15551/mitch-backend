package ru.mitch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitch.model.RefMetric;

public interface RefMetricRepository extends JpaRepository<RefMetric, Long> {

    RefMetric findByMetricCode(String metricCode);

}
