package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MemberDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberDTO, Long> {
    public Optional<MemberDTO> findByAccount(String account);

}
