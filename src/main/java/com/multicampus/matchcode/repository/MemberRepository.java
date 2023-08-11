package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberDTO, Long> {}
