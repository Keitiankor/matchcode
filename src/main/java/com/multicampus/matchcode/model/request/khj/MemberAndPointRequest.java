package com.multicampus.matchcode.model.request.khj;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import lombok.Data;

@Data
public class MemberAndPointRequest {

    private MemberDTO memberDTO; //세션에서 꺼내서 쓰면 됨
    private PointDTO pointDTO;


    private long memberid;
    private int point;


    public MemberAndPointRequest(MemberDTO memberDTO, PointDTO pointDTO) {
        this.memberDTO = memberDTO;
        this.pointDTO = pointDTO;
    }

    public MemberDTO getMemberDTO() {
        return memberDTO;
    }

    public PointDTO getPointDTO() {
        return pointDTO;
    }
    //막상 해놓고보니 request 클래스는 사용을 안하는데...? 필요가 없다....뭐지?

}