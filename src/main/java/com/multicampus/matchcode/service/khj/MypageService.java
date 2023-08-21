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

    //매치 히스토리 화면 내 매치 기록을 불러오기 위한 service메소드
    public MatchDTO getMatch(long id){
        Optional<MatchDTO> dto = match.findById(id);
        if(dto.isPresent()) {
            return dto.get();
        }else{
            return null;
        }
    }

}
