package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.FavoriteSportsDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteSportsRepository extends JpaRepository<FavoriteSportsDTO, Long> {
}
