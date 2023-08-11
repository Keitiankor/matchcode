package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PostDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostDTO, Long> {}
