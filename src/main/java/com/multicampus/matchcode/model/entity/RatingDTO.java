package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Rating")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingDTO {

    @Id
    private long id;

    private long userId;
    private long sportsId;
    private long emblemId;
    private int mmr;
}
