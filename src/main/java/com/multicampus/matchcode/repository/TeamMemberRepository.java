package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.TeamMemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMemberDTO, Long> {
    Optional<TeamMemberDTO> findByMemberId(long memberId);

    boolean existsByMemberId(long memberId);

    @Query("select m.privilege from TeamMember m where m.teamId = :teamId and m.memberId =:memberId")
    public abstract Integer findPrivilegeByTeamIdAndMemberId(
            @Param("teamId") long teamId,
            @Param("memberId") long memberId
    );
}
