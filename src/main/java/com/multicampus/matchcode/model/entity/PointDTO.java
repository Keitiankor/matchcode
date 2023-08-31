package com.multicampus.matchcode.model.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;


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
    private long memberId;
    private long userId;
    private Timestamp date;
    private int point;
    private String details;
}
