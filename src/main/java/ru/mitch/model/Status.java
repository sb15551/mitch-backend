package ru.mitch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ref_status")
public class Status {

    @Id
    private Long id;

    private String type;

    private String code;

    private String name;
}
