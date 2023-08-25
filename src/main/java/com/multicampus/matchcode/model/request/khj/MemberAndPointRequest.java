package com.multicampus.matchcode.model.request.khj;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberAndPointRequest {

    private MemberDTO memberDTO; //세션에서 꺼내서 쓰면 됨
    private PointDTO pointDTO;

    private long memberid;
    private int point;
}
