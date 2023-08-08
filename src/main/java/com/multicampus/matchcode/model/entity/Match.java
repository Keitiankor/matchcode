package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import java.sql.Timestamp;
import lombok.Getter;

@Entity
@Getter
public class Match {

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
