package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Review {

    private long id;
    private long userId;
    private long mapId;
    private String comment;
    private int rate;
}
