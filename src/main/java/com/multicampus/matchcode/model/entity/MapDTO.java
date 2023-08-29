package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long areaId;
    private long sportsId;
    private double latitude;
    private double longitude;
    private String SportCenterName;
    private String managerName;
    private String phone;
    private String resistrationCode;
    private int price;
    private int status;
}
