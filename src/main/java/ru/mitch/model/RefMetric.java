package ru.mitch.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class RefMetric {

    @Id
    private Long id;

    private String metricCode;

    private String metricName;

    private Integer metricOrder;

}
