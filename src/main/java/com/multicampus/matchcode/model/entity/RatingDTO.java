package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class RatingDTO {

    private long id;
    private long userId;
    private long sportsId;
    private long emblemId;
    private int mmr;
}
