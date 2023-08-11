package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "FavoriteSports")
@Getter
public class FavoriteSportsDTO {

    @Id
    private long id;

    private long userId;
    private long sportsId;
    private String Text;
}
