package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.*;
import com.multicampus.matchcode.model.request.khj.MemberAndPointRequest;
import com.multicampus.matchcode.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                return new MemberAndPointRequest(memberDTO.get(), pointDTO.get());
            } else {

                return new MemberAndPointRequest(memberDTO.get(), null);
            }
        } else {
            return null;
        }
    }

}
