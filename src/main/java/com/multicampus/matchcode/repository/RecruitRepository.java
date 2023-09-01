package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.RecruitDTO;
import com.multicampus.matchcode.model.entity.TeamDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecruitRepository extends JpaRepository<RecruitDTO, Long> {
    Optional<RecruitDTO> findByTeamId(long teamId);
}
