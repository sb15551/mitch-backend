package ru.samarkand.tightorboil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ref_role")
public class Role {

    @Id
    private Long id;

    private String name;

    private String code;

}
