package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class ReviewDTO {

    private long id;
    private long userId;
    private long mapId;
    private String comment;
    private int rate;
}
