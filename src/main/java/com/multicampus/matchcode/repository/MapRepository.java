package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.entity.ResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<MapDTO, Long> {

//    MapDTO findByMatchId(long matchId);
}
