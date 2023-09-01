package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepository extends JpaRepository<ResultDTO, Long> {
    Optional<ResultDTO> findByMatchIdAndMemberId(long matchId, long memberId);
}
