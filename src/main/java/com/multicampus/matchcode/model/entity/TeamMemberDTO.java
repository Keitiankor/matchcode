package com.multicampus.matchcode.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "TeamMember")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_member_id")
    private long id;

    private long teamId;
    private long userId;
    private int privilege;
}
