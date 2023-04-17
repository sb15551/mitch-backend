package ru.mitch.model.view;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class CommonView {

    private String name;

    private String surname;

    private Integer countTop;

}
