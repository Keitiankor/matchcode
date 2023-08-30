package com.multicampus.matchcode.model.request.ljg;

import com.multicampus.matchcode.model.entity.MemberDTO;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReserveRequest {
   private Timestamp matchDate;
   private long mapId;
   private long userId;
   private int pricePoints;
   private MemberDTO memberId;
}
