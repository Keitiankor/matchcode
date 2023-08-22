package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.MemberAndPointDTO;
import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.repository.MatchRepository;
import com.multicampus.matchcode.repository.MemberRepository;
import com.multicampus.matchcode.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MypageService {

    @Autowired
    MemberRepository member;

    @Autowired
    PointRepository point;

    @Autowired
    MatchRepository match;


    //메인화면 member 이름과 point를 불러오기 위한 service메소드
    public MemberAndPointDTO getMemberAndPoint(long memberId) {
        Optional<MemberDTO> memberDTO = member.findById(memberId);

        if (memberDTO.isPresent()) {
            Optional<PointDTO> pointDTO = point.findByUserId(memberId);

            if (pointDTO.isPresent()) {
                return new MemberAndPointDTO(memberDTO.get(), pointDTO.get());
            } else {
                // If point info is not found, you can pass an empty list or handle it based on your requirement
                return new MemberAndPointDTO(memberDTO.get(), null);
            }
        } else {
            return null;
        }
    }

    //매치 히스토리 화면 내 종목에 맞는 기록을 불러오기 위한 service메소드
    //문제1) 그런데 '나의' 매치 기록을 가져와야 하는데...?
    //문제2) 다른 테이블에서도 값들 다 가져와야하는데...?
    public MatchDTO getMatchBySportsId(long sportsId){
        Optional<MatchDTO> matchDTO = match.findBySportsId(sportsId);
        if(matchDTO.isPresent()) {
            return matchDTO.get();
        }else{
            return null;
        }
    }

//    public MatchDTO getMatchBySportsId(long sportsId) {
//        return match.findBySportsId(sportsId);
//    }
}
