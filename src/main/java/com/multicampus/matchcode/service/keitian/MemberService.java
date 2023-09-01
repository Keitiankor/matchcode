package com.multicampus.matchcode.service.keitian;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.request.keitian.RegisterRequest;
import com.multicampus.matchcode.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Slf4j
@Service
public class MemberService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder pe;


    public void insert(RegisterRequest request) {
        Timestamp bd = null;
        try {
            bd = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirthday()).getTime());
        } catch (ParseException ex) {
            log.error("Parse Error ! {}", ex.getMessage());
        }

        MemberDTO dto = MemberDTO.builder().account(request.getAccount()).password(pe.encode(request.getPassword())).name(request.getName()).phone(request.getPhone()).mailAddress(request.getMailAddress()).createdDate(new Timestamp(System.currentTimeMillis())).birthday(bd).build();

        repository.save(dto);
    }

    public MemberDTO getId(String acc, String pw) {
        Optional<MemberDTO> odto = repository.findByAccount(acc);
        if (odto.isPresent()) {
            MemberDTO dto = odto.get();
            if (pe.matches(pw, dto.getPassword())) {
                return dto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean isAccountDup(String acc) {
        Optional<MemberDTO> odto = repository.findByAccount(acc);
        return odto.isEmpty();
    }
}
