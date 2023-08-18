package com.multicampus.matchcode.service.keitian;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.request.keitian.RegistserRequest;
import com.multicampus.matchcode.repository.MemberRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepository repository;

    public void insert(RegistserRequest request) {
        MemberDTO dto = MemberDTO
            .builder()
            .areaId(request.getAreaId())
            .account(request.getAccount())
            .password(request.getPassword())
            .name(request.getName())
            .phone(request.getPhone())
            .mailAddress(request.getMailAddress())
            .birthday(request.getBirthday())
            .build();

        repository.save(dto);
    }

    public long getId(String acc, String pw) {
        Optional<MemberDTO> odto = repository.findByAccountAndPassword(acc, pw);
        if (odto.isPresent()) {
            MemberDTO dto = odto.get();
            return dto.getId();
        } else {
            return -1;
        }
    }
}
