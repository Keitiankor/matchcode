package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "TeamMember")
@Getter
public class TeamMemberDTO {

    @Id
    private long id;

    private long teamId;
    private long userId;
    private int privilege;
}
