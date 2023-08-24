package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.ResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<ResultDTO, Long> {

    ResultDTO findByMatchId(long matchId);
}
