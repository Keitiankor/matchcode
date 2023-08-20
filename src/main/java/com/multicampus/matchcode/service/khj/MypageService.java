package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MypageService {
    @Autowired
    MemberRepository member;
    public MemberDTO findById(long id){
        Optional<MemberDTO> dto = member.findById(id);
        if(dto.isPresent()) {
            return dto.get();
        }else{
            return null;
        }
    }
}
