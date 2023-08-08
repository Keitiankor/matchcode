package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class FavoriteSportsDTO {

    private long id;
    private long userId;
    private long sportsId;
    private String Text;
}
