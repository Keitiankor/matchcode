package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "UsePoint")
@Data
@NoArgsConstructor
public class PointUseHistoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;
    private Timestamp paydate;
    private int price;
}
