package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Review")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    @Id
    private long id;

    private long userId;
    private long mapId;
    private String comment;
    private int rate;
}
