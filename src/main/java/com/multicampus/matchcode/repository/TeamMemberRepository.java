package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.TeamMemberDTO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMemberDTO, Long> {
    Optional<TeamMemberDTO> findByMemberId(long memberId);
}
