package com.multicampus.matchcode.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Application")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private long id;

    private long userId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamDTO teamId;

    private String rejectReason;
    private String introduction;
    private int status;
}
