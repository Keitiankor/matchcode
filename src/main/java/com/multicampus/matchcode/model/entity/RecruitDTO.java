package com.multicampus.matchcode.model.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "Recruit")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recruit_id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TeamDTO teamId;

    private String content;

    @CreationTimestamp
    private Timestamp createdDate;

    private Timestamp modifiedDate;
    private int status;
}
