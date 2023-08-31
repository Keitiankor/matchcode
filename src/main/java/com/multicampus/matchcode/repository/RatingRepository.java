package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.RatingDTO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingDTO, Long> {
    Optional<RatingDTO> findBySportsIdAndMemberId(long sportsId, long memberId);
}
