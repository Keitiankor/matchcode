package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.MemberAndPointDTO;
import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.repository.MemberRepository;
import com.multicampus.matchcode.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MypageService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PointRepository pointRepository;

    public MemberAndPointDTO getMemberAndPoint(long memberId) {
        Optional<MemberDTO> memberDTO = memberRepository.findById(memberId);

        if (memberDTO.isPresent()) {
            Optional<PointDTO> pointDTO = pointRepository.findById(memberDTO.get().getId());
            // 둘 다 단순 findById 지만(둘 다 임의로 데이터를 넣었음.),나중에 foreign key로 엮어서 작동하게 해야함
            if (pointDTO.isPresent()) {
                return new MemberAndPointDTO(memberDTO.get(), pointDTO.get());
            } else {
                return new MemberAndPointDTO(memberDTO.get(), new PointDTO()); // If point info is not found
            }
        } else {
            return null;
        }
    }
}
