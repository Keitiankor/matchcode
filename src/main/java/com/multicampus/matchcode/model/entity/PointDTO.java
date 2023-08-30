package com.multicampus.matchcode.model.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.*;


@Entity(name="Point")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class PointDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberDTO memberId;
    private Timestamp date;
    private int point;
    private int refundAmount;

//
    //
private long mapId;
private Timestamp matchDate;


}
