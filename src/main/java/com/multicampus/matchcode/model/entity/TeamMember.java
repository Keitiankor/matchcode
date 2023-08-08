package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class TeamMember {

    private long teamId;
    private long userId;
    private int privilege;
}
