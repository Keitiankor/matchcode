package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import java.sql.Timestamp;
import lombok.Getter;

@Entity
@Getter
public class PointDTO {

    private long id;
    private long userId;
    private Timestamp date;
    private Timestamp expireDate;
    private int point;
}
