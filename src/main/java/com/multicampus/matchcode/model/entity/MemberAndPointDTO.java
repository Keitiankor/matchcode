package com.multicampus.matchcode.model.entity;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PointDTO;

public class MemberAndPointDTO { //mypage 메인화면에 이름과 포인트를 함께 표시하기위한 DTO

    private MemberDTO memberDTO; //세션에서 꺼내서 쓰면 됨
    private PointDTO pointDTO;

    public MemberAndPointDTO(MemberDTO memberDTO, PointDTO pointDTO) {
        this.memberDTO = memberDTO;
        this.pointDTO = pointDTO;
    }

    public MemberDTO getMemberDTO() {
        return memberDTO;
    }

    public PointDTO getPointDTO() {
        return pointDTO;
    }
}
