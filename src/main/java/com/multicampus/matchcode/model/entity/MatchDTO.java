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

@Entity(name = "Match_") // match가 예악어라서 그냥 두면 충돌이 남(테이블 안생김 ㅠ)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long mapId;
    private long sportsId;
    private String content;
    private Timestamp matchDate;
    @CreationTimestamp
    private Timestamp createdDate;
    private Timestamp expireDate;
    private int restrictionMinRate;
    private int restrictionMaxRate;
    private long status;
}
