package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ApplicationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<ApplicationDTO, Long> {
    Page<ApplicationDTO> findByTeamId(long teamId, Pageable pageable);
}
