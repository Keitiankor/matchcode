package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity(name = "TeamTest")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TeamDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long mapId;
    private List<Long> sportsId;
    private String teamName;
    private String uri;
    private String emblem;
    private List<Long> useWeek;
    private long useTime;
    private int teamRank;
    private List<Long> averageAge;
    private long averageGender;

    @CreationTimestamp
    private Timestamp createdDate;

    private Integer status;
}
