package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.Getter;

@Entity(name = "Recruit")
@Getter
public class RecruitDTO {

    @Id
    private long teamId;

    private String content;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private int status;
}
