package com.multicampus.matchcode.controller.jun;

import com.multicampus.matchcode.model.entity.ReviewDTO;
import com.multicampus.matchcode.model.request.jun.ReviewRequest;
import com.multicampus.matchcode.model.request.jun.UpdateReviewRequest;
import com.multicampus.matchcode.service.jun.ReviewSercvice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "review")
public class ReviewController {

    @Autowired
    private ReviewSercvice reviewSercvice;

    //리뷰할 체육 시설 위치 표시
    @GetMapping("viewSportCenter")
    public String viewSportCenter(Model model) {
        return "review/viewSportCenter";
    }

    //이용한 체육시설 리뷰 페이지 이동
    @GetMapping("createReview")
    public String createReview() {
        return "review/createReview";
    }

    //이용한 체육시설 평점 남기기
    @PostMapping("createReview2")
    public String createReview2(ReviewRequest reviewRequest) {
        System.out.println("rate : " + reviewRequest.getRate());
        System.out.println("comment : " + reviewRequest.getComment());

        reviewSercvice.save(reviewRequest);

        return "redirect:/review/listReview";
    }

    // 리뷰 수정 폼으로 이동
    @GetMapping("updateReview")
    public String updateReview(@RequestParam Long id, Model model) {
        ReviewDTO review = reviewSercvice.findById(id);
        model.addAttribute("review", review);
        return "review/updateReview";
    }

    // 리뷰 수정 처리
    @PostMapping("updateReview")
    public String updateReview(UpdateReviewRequest updateReviewRequest) {
        reviewSercvice.update(updateReviewRequest);
        return "redirect:/review/listReview";
    }

    //예전 사용자가 썼던 리뷰 삭제
    @GetMapping("deleteReview")
    public String deleteReview(Long id) {
        reviewSercvice.delete(id);
        return "redirect:/review/listReview";
    }

    //리뷰 리스트 가져오기
    @GetMapping("listReview")
    public String showReviewList(Model model, ReviewDTO reviewDTO) {
        List<ReviewDTO> reviewList = reviewSercvice.select(reviewDTO);
        model.addAttribute("reviewList", reviewList);
        return "review/listReview";
    }
}
