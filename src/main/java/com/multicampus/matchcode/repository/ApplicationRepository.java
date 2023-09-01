package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ApplicationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<ApplicationDTO, Long> {
}
