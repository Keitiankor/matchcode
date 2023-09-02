package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchMemberRepository extends JpaRepository<MatchMemberDTO, Long> {
    List<MatchMemberDTO> findAllByMatchId(long matchId);
}
