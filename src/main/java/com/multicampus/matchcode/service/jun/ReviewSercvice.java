package com.multicampus.matchcode.service.jun;

import com.multicampus.matchcode.model.entity.ReviewDTO;
import com.multicampus.matchcode.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewSercvice {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewDTO save(ReviewDTO reviewDTO) {
        System.out.println("2 " + reviewDTO);
        return reviewRepository.save(reviewDTO);
    }

    public ReviewDTO delete(ReviewDTO reviewDTO){

        return null;
    }
}
