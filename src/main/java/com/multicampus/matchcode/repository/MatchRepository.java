package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.request.hyuk.Match;
import com.multicampus.matchcode.model.entity.MatchDTO;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchDTO, Long> {

    List<Match> findByMapIdContaining(String keyword);
    List<MatchDTO> findBySportsId(long sportsId);
    Page<MatchDTO> findByMapId(long mapId, Pageable pageable);

    // mapId를 기준으로 지역별 데이터 개수 세기
    long countByMapId(long mapId);
}
