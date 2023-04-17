package ru.mitch.model.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@Table(name = "losers")
@Immutable
public class Loser extends CommonView {

    @Id
    private Long playerId;

}
