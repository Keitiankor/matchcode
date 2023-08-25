package com.multicampus.matchcode.model.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "Team_test")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TeamDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
