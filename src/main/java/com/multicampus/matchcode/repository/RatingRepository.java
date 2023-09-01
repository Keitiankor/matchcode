package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.RatingDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<RatingDTO, Long> {
    Optional<RatingDTO> findBySportsIdAndMemberId(long sportsId, long memberId);
}
