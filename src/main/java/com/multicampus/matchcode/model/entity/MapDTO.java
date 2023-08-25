package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Map")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapDTO {

    @Id
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
