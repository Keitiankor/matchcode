package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MatchDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<MatchDTO, Long> {
    List<MatchDTO> findBySportsId(long sportsId);
}
