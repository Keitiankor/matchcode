package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PointDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointDTO, Long> {
    Optional<PointDTO> findByUserId(long userId);

    public Optional<List<PointDTO>> findAllByUserId(Long userId);
}
