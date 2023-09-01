package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.TeamMemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMemberDTO, Long> {
    Optional<TeamMemberDTO> findByMemberId(long memberId);
    default boolean existsByMemberId(long memberId) {
        return false;
    }
}
