package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PointDTO;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointDTO, Long> {
    public Optional<ArrayList<PointDTO>> findAllByUserId(Long userId);
}
