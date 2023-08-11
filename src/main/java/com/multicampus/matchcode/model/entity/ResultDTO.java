package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "Result")
@Getter
public class ResultDTO {

    @Id
    private long id;

    private long matchId;
    private long userId;
    private int status;
    private int myScore;
    private int rivalScore;
}
