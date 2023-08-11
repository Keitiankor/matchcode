package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MapDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<MapDTO, Long> {}
