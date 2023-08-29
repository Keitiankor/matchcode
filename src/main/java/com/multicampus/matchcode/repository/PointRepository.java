package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PointDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PointRepository extends JpaRepository<PointDTO, Long> {

    public Optional<List<PointDTO>> findAllByUserId(Long userId);
}
