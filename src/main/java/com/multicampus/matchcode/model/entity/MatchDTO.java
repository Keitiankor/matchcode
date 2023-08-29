package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import java.sql.Timestamp;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Match_")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDTO {
@Id
@OneToOne (mappedBy = "match")
    private long id;
    private long mapId;
    private long sportsId;
    private Timestamp matchDate;
    private Timestamp createdDate;
    private Timestamp expireDate;
    private int restrictionMinRate;
    private int restrictionMaxRate;
    private int Status;
}