package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "Review")
@Getter
public class ReviewDTO {

    @Id
    private long id;

    private long userId;
    private long mapId;
    private String comment;
    private int rate;
}
