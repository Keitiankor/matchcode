package com.multicampus.matchcode.model.request.ljg;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReserveRequest {
    private long mapId;
    private long memberId;
    private int price;
    //private Timestamp matchDate; // matchDate를 Timestamp로 변경

}
