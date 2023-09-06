package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MatchMemberRepository extends JpaRepository<MatchMemberDTO, Long> {
    List<MatchMemberDTO> findAllByMatchId(long matchId);
    Optional<MatchMemberDTO> findByMatchId(long matchId);

    Optional<MatchMemberDTO> findByMatchIdAndMemberId(long matchId, long memberId);
    Optional<MatchMemberDTO> findByMemberId(long memberId);

    boolean existsByMemberId(long memberId);
}
