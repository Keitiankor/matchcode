package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class ApplicationDTO {

    private long userId;
    private long teamId;
    private String rejectReason;
    private String introduction;
    private int status;
}
