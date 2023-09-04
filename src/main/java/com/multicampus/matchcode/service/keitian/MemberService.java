package com.multicampus.matchcode.service.keitian;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.request.keitian.RegisterRequest;
import com.multicampus.matchcode.repository.MemberRepository;
import com.multicampus.matchcode.util.component.MailComponent;
import com.multicampus.matchcode.util.constants.StringConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class MemberService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder pe;

    @Autowired
    private MailComponent mailComponent;

    public void insert(RegisterRequest request) {
        Timestamp bd = null;
        try {
            bd = new Timestamp(new SimpleDateFormat("yyyy-MM-dd")
                                       .parse(request.getBirthday())
                                       .getTime());
        } catch (ParseException ex) {
            log.error("Parse Error ! {}", ex.getMessage());
        }
        MemberDTO dto = MemberDTO
                .builder()
                .account(request.getAccount())
                .password(pe.encode(request.getPassword()))
                .name(request.getName())
                .phone(request.getPhone())
                .mailAddress(request.getMailAddress())
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .birthday(bd)
                .build();
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

    public Optional<MemberDTO> isAccountDup(String acc) {
        return repository.findByAccount(acc);
    }

    @Transactional
    public String findPassword(String account, String mailAddress) {
        var ref = new Object() {
            String generatedPassword = StringConstant.NO_ACCOUNT;
        };
        repository
                .findByAccount(account)
                .ifPresent(memberDTO -> {
                    if (memberDTO
                            .getMailAddress()
                            .equals(mailAddress)) {
                        Random random = new Random();
                        ref.generatedPassword = random
                                .ints(97, 122)
                                .limit(12)
                                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                .toString();
                        mailComponent.sendTempPassword(mailAddress, ref.generatedPassword);
                        repository.updatePasswordById(memberDTO.getId(), pe.encode(ref.generatedPassword));
                    } else {
                        ref.generatedPassword = StringConstant.NO_MATCH_EMAIL;
                    }
                });
        return ref.generatedPassword;
    }

    @Transactional
    public boolean changePassword(MemberDTO dto, String oldPassword, String newPassword) {
        if (pe.matches(oldPassword, dto.getPassword())) {
            repository.updatePasswordById(dto.getId(), pe.encode(newPassword));
            return true;
        }
        return false;
    }
}
