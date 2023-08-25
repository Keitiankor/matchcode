package com.multicampus.matchcode.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Data

public class ChargeHistoryDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private long userId;
    private int amount;

    public ChargeHistoryDTO() {
        this.id = id;
        this.timestamp = timestamp;
        this.userId = userId;
        this.amount = amount;
    }


}