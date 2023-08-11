package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;
import lombok.Getter;

@Entity(name = "Team")
@Getter
public class TeamDTO {

    @Id
    private long id;

    private long mapId;
    private long sportsId;
    private String teamName;
    private String uri;
    private String emblem;
    private int useWeek;
    private Time useTime;
    private int teamRank;
    private short averageAge;
    private short averageGender;
    private Timestamp createdDate;
    private int status;
}
