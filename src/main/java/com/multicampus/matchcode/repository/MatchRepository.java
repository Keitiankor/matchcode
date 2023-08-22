package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MatchDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<MatchDTO, Long> {
    Optional<MatchDTO> findBySportsId(long sportsId);
}
