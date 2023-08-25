package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.*;

@Entity
@Getter
@Data
//@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class PointDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;
    private Timestamp date;
    private int point;
}
