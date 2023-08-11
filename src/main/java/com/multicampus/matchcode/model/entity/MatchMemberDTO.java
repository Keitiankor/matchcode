package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "MatchMember")
@Getter
public class MatchMemberDTO {

    @Id
    private long id;

    private long matchId;
    private long userId;
}
