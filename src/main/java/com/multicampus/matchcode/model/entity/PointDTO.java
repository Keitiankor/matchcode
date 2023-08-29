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
    private Timestamp date;
    private Timestamp expireDate;
    private int point;

//
    //
private long mapId;
private Timestamp matchDate;


}
