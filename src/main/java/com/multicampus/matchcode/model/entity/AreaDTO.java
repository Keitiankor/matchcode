package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class AreaDTO {

    private long id;
    private String city;
}
