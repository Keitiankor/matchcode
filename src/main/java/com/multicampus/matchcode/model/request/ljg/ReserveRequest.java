package com.multicampus.matchcode.model.request.ljg;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReserveRequest {
   private Timestamp matchDate;
   private long mapId;
   private long userId;
   private int pricePoints;
}
