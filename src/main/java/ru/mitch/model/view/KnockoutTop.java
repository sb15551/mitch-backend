package ru.mitch.model.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@Immutable
public class KnockoutTop extends CommonView {

    @Id
    private Long playerId;

}
