package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "Team")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long mapId;
    private String sportsId;
    private String teamName;
    private String uri;
    private String emblem;
    private String useWeek;
    private String useTime;
    private int teamRank;
    private String averageAge;
    private String averageGender;

    @CreationTimestamp
    private Timestamp createdDate;
    private int status;

    @Builder
    public TeamDTO(String sportsId, String teamName, String uri,
                   String useWeek, String useTime, String averageAge,
                   String averageGender, Timestamp createdDate) {
        this.sportsId = sportsId;
        this.teamName = teamName;
        this.uri = uri;
        this.useWeek = useWeek;
        this.useTime = useTime;
        this.averageAge = averageAge;
        this.averageGender = averageGender;
        this.createdDate = createdDate;
    }
}
