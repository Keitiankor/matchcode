package com.multicampus.matchcode.model.request.khj;

import lombok.Data;

@Data
public class MemberAndPointRequest {
    private long memberid;
    private int point;

    //막상 해놓고보니 request 클래스는 사용을 안하는데...? 필요가 없다....뭐지?

}