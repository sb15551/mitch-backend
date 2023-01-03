package ru.samarkand.tightorboil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ref_location")
public class Location {

    @Id
    private Long id;

    private String name;

}
