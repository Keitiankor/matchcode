package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.ResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<ResultDTO, Long> {

    Optional<ResultDTO> findByMatchIdAndUserId(long matchId, long userId);
}
