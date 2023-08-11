package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "Rating")
@Getter
public class RatingDTO {

    @Id
    private long id;

    private long userId;
    private long sportsId;
    private long emblemId;
    private int mmr;
}
