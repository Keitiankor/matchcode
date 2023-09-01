package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ReplyDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyDTO, Long> {


    List<ReplyDTO> findByPostId(long postId);


}