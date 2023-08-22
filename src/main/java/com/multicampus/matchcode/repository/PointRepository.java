package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PointDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<PointDTO, Long> {
    public Optional<PointDTO> findByUserId(long memberId);//memberId와 pointDTO 내의 userId가 일치하는 데이터를 가져오겠다.
}
