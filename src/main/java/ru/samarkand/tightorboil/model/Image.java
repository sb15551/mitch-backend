package ru.samarkand.tightorboil.model;

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
