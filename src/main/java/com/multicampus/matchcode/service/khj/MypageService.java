package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.*;
import com.multicampus.matchcode.model.request.khj.MemberAndPointRequest;
import com.multicampus.matchcode.repository.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    @Autowired
    MemberRepository member;

    @Autowired
    PointRepository point;

    @Autowired
    MatchRepository matches;

    @Autowired
    MapRepository map;

    @Autowired
    ResultRepository result;

    @Autowired
    MatchMemberRepository matchmember;

    //메인화면 member 이름과 point를 불러오기 위한 service메소드
    public MemberAndPointRequest getMemberAndPoint(long memberId) {
        Optional<MemberDTO> memberDTO = member.findById(memberId);

        if (memberDTO.isPresent()) {
            Optional<PointDTO> pointDTO = point.findByUserId(memberId);

            if (pointDTO.isPresent()) {
                return MemberAndPointRequest.builder().pointDTO(pointDTO.get()).memberDTO(memberDTO.get()).build();
            } else {
                return MemberAndPointRequest.builder().memberDTO(memberDTO.get()).build();
            }
        } else {
            // memberId에 해당하는 회원 정보가 없는 경우 처리
            return MemberAndPointRequest.builder().build();
        }
    }
}
