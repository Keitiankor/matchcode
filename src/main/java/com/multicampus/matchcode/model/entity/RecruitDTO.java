package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import java.sql.Timestamp;
import lombok.Getter;

@Entity
@Getter
public class RecruitDTO {

    private long teamId;
    private String content;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private int status;
}
