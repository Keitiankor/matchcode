package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import com.multicampus.matchcode.model.entity.RecruitDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface MatchMemberRepository extends JpaRepository<MatchMemberDTO, Long> {
    List<MatchMemberDTO> findAllByMatchId(long matchId);
    Optional<MatchMemberDTO> findByMatchId(long matchId);
}
