package ru.mitch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Image {

    @Id
    private Long id;

    private String link;

}
