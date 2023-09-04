package com.multicampus.matchcode.service.jun;

import com.multicampus.matchcode.model.entity.ReviewDTO;
import com.multicampus.matchcode.model.request.jun.ReviewRequest;
import com.multicampus.matchcode.model.request.jun.UpdateReviewRequest;
import com.multicampus.matchcode.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void save(ReviewRequest request, long memberId) {
        ReviewDTO reviewdto = ReviewDTO
                .builder()
                .memberId(memberId)
                .mapId(34)
                .rate(request.getRate())
                .comment(request.getComment())
                .build();
        log.info("Review.insert : {}", reviewdto.toString());
        reviewRepository.save(reviewdto);
    }

    public void delete(Long id) {
        log.info("Review.delete : {}", id);
        reviewRepository.deleteById(id);
    }

    // 리뷰 수정 처리 메서드
    public void update(UpdateReviewRequest updateReviewRequest) {
        ReviewDTO review = ReviewDTO
                .builder()
                .id(updateReviewRequest.getId())
                .memberId(updateReviewRequest
                                  .getDto()
                                  .getMemberId())
                .mapId(updateReviewRequest
                               .getDto()
                               .getMapId())
                .rate(updateReviewRequest.getRate())
                .comment(updateReviewRequest.getComment())
                .build();
        reviewRepository.save(review);
    }

    public List<ReviewDTO> select(ReviewDTO reviewDTO) {
        return reviewRepository.findAll();
    }

    // 해당 id에 해당하는 리뷰 정보를 가져오는 메서드
    public ReviewDTO findById(Long id) {
        return reviewRepository
                .findById(id)
                .orElse(null);
    }
}
