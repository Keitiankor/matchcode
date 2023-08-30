package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.RatingDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingDTO, Long> {
    RatingDTO findBySportsIdAndUserId(long sportsId, long userId);
}
