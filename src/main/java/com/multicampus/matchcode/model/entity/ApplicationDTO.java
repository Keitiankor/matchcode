package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "Application")
@Getter
public class ApplicationDTO {

    @Id
    private long id;

    private long userId;
    private long teamId;
    private String rejectReason;
    private String introduction;
    private int status;
}
