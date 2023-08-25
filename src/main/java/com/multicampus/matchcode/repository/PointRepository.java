package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PointDTO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointDTO, Long> {
    PointDTO findByuserId(long userId);
    public Optional<ArrayList<PointDTO>> findAllByUserId(Long userId);
}
