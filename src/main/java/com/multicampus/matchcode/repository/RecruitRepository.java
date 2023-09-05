package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.RecruitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Objects;
import java.util.Optional;

public interface RecruitRepository extends JpaRepository<RecruitDTO, Long> {
    Optional<RecruitDTO> findByTeamId(long teamId);

    @Modifying
    @Query("delete Recruit r where r.teamId = :teamId")
    void deleteRecruitsByTeamId(@Param("teamId") long id);

    @Query("select r.id, t.teamName, t.sportsId, t.averageGender, t.averageAge, t.useWeek, t.useTime, r.status " +
            "from TeamTest t inner join Recruit r " +
            "on r.teamId = t.id")
    Page<Objects[]> findByAllWithTeamInfo(Pageable pageable);

    @Query("select r.id, t.teamName, t.sportsId, t.averageGender, t.averageAge, t.useWeek, t.useTime, r.status," +
            "t.uri, t.id" +
            " from TeamTest t left outer join Recruit r " +
            "on r.teamId = t.id")
    Page<Objects[]> findByIsRecruitWithTeamInfo(Pageable pageable);
}
