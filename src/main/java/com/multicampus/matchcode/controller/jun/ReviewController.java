package com.multicampus.matchcode.controller.jun;

import com.multicampus.matchcode.model.entity.ReviewDTO;
import com.multicampus.matchcode.service.jun.ReviewSercvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "review", method = {RequestMethod.GET, RequestMethod.POST})
public class ReviewController {

    @Autowired
    private ReviewSercvice reviewSercvice;

    //리뷰할 체육 시설 위치 표시
    @GetMapping("viewSportCenter")
    public String viewSportCenter(Model model) {

        return "review/viewSportCenter";
    }

    //이용한 체육시설 홈페이지 이동
    @GetMapping("createReview")
    public String createReview() {

        return "review/createReview";
    }

    //이용한 체육시설 평점 남기기
    @GetMapping("createReview2")
    public String createReview2(ReviewDTO reviewDTO, Model model) {
       /* reviewDTO.setId(1);
        reviewDTO.setMapId(1);
        reviewDTO.setUserId(1);*/
        System.out.println("평점: " + reviewDTO.getRate());
        System.out.println("리뷰 : " + reviewDTO.getComment());
        model.addAttribute("review", reviewDTO);

        ReviewDTO reviewDTO2 = reviewSercvice.save(reviewDTO);
        System.out.println("3 " + reviewDTO2);
        return "review/viewSportCenter";
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
