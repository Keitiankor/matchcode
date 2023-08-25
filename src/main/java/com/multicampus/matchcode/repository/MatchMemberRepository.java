package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchMemberRepository extends JpaRepository<MatchMemberDTO, Long> {

    MatchMemberDTO findByMatchId(long matchId);
}
