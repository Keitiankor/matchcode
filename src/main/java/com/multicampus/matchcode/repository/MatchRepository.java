package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.domain.Match;
import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<MatchDTO, Long> {
    List<Match> findByMapIdContaining(String keyword);
    //MatchMemberDTO findByUserId(long userId);
}
