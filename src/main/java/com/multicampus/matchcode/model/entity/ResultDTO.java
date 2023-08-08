package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class ResultDTO {

    private long matchId;
    private long userId;
    private int status;
    private int myScore;
    private int rivalScore;
}
