package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberDTO {

    @Id
    private long id;

    private long teamId;
    private long userId;
    private int privilege;
}
