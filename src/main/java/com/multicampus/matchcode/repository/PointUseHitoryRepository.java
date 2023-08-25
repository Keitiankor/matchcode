package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PointUseHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointUseHitoryRepository extends JpaRepository<PointUseHistoryDTO, Long> {
    List<PointUseHistoryDTO> findAllByUserId(long userId);
}