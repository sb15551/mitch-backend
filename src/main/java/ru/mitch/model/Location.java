package ru.mitch.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ref_location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

}
