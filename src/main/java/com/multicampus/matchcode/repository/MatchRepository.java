package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.domain.Match;
import com.multicampus.matchcode.model.entity.MatchDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchDTO, Long> {
    List<Match> findByMapIdContaining(String keyword);

    List<MatchDTO> findBySportsId(long sportsId);
}
