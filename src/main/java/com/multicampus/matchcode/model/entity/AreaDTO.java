package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "Area")
@Getter
public class AreaDTO {

    @Id
    private long id;

    private String city;
}
