package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PointDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointDTO, Long> {}
