package ru.mitch.model.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "top_places")
public class TopPlace extends CommonView {

    @Id
    private UUID uuid;

    private Long playerId;

    private Integer place;

}
