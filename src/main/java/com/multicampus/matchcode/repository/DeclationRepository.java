package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.DeclationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclationRepository extends JpaRepository<DeclationDTO, Long> {
    DeclationDTO findByPostIdAndMemberId(long postId, long memberId);
}
