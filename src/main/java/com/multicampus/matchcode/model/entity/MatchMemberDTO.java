package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class MatchMemberDTO {

    private long matchId;
    private long userId;
}
