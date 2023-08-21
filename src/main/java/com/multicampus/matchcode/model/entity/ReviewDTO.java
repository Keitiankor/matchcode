package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ReviewDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;
    private long mapId;
    private String comment;
    private int rate;

    public ReviewDTO(long id, long userId, long mapId, String comment, int rate) {
        this.id = id;
        this.userId = userId;
        this.mapId = mapId;
        this.comment = comment;
        this.rate = rate;
    }

}
