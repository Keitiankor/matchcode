package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.Getter;

@Entity(name = "Point")
@Getter
public class PointDTO {

    @Id
    private long id;

    private long userId;
    private Timestamp date;
    private Timestamp expireDate;
    private int point;
}
