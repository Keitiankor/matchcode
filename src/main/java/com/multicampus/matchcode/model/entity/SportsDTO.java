package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "Sports")
@Getter
public class SportsDTO {

    @Id
    private long id;

    private String name;
}
