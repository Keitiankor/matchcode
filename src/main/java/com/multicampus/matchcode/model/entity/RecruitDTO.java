package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Recruit")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitDTO {

    @Id
    private long teamId;

    private String content;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private int status;
}
