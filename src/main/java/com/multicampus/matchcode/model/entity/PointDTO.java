package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Point")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointDTO {

    @Id
    private long id;

    private long userId;
    private Timestamp date;
    private Timestamp expireDate;
    private int point;
}
