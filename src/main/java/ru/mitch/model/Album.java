package ru.mitch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Album {

    @Id
    private Long id;

    @ManyToOne
    private Image image;

}
