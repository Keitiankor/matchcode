package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ResultDTO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultDTO, Long> {
    Optional<ResultDTO> findByMatchIdAndUserId(long matchId, long userId);
}
