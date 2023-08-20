package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import java.sql.Timestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Getter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class PointDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private Timestamp date;
    private Timestamp expireDate;
    private int point;
    private int amount;

    public PointDTO(long id, long userId) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.expireDate = expireDate;
        this.point = point;
        this.amount = amount;
    }
}
