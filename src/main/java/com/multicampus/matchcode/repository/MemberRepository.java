package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberDTO, Long> {
    public Optional<MemberDTO> findByAccount(String account);

}
