package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.RecruitDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecruitRepository extends JpaRepository<RecruitDTO, Long> {
    Optional<RecruitDTO> findByTeamId(long teamId);

    @Modifying
    @Query("SELECT r From Recruit r where r.teamId = :teamId")
    Optional<RecruitDTO> deleteRecruitsByTeamId(@Param("teamId") long id);
}
