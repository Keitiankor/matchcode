package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PointUseHistoryDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointUseHitoryRepository extends JpaRepository<PointUseHistoryDTO, Long> {
    List<PointUseHistoryDTO> findAllByUserId(long userId);
}
