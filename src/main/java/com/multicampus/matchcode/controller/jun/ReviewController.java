package com.multicampus.matchcode.controller.jun;

import com.multicampus.matchcode.model.entity.ReviewDTO;
import com.multicampus.matchcode.service.jun.ReviewSercvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("review")
public class ReviewController {

    @Autowired
    ReviewSercvice reviewSercvice;

    //리뷰할 체육 시설 위치 표시
    @GetMapping("viewSportCenter")
    public String viewSportCenter(Model model) {

        return "review/viewSportCenter";
    }

    //이용한 체육시설 평점 남기기
    @GetMapping("createReview")
    public String createReview(ReviewDTO reviewDTO, Model model) {

        return "review/createReview";
    }

    //예전 사용자가 썼던 리뷰 수정
    @GetMapping("updateReview")
    public String updateReview() {

        return "review/updateReview";
    }

    //예전 사용자가 썼던 리뷰 삭제
    @GetMapping("deleteReview")
    public String deleteReview() {

        return "review/deleteReview";
    }
}
