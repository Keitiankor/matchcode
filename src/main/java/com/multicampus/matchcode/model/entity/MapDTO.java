package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class MapDTO {

    private long id;
    private long areaId;
    private long sportsId;
    private double latitude;
    private double longitude;
    private String name;
    private String phone;
    private String resistrationCode;
    private int price;
    private int status;
}
