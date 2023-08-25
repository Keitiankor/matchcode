package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewDTO, Long> {
}
