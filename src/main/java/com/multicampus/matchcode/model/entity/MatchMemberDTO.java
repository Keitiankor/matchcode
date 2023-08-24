package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "MatchMember")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchMemberDTO {

    @Id
    private long id;

    private long matchId;
    private long userId;


}
