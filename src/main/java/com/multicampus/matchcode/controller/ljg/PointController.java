package com.multicampus.matchcode.controller.ljg;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.model.entity.PointUseHistoryDTO;
import com.multicampus.matchcode.service.ljg.PointService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//포인트 조회
@Controller
public class PointController {

    @Autowired
    private PointService pointService;

    //포인트표시
    @GetMapping("/point")
    public String viewPointPage(
        @SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) MemberDTO member,
        Model model
    ) {
        long userId = 1;
        List<PointDTO> chargeHistories = pointService.findAllByUserId(1/*member.getId()*/);
        int totalPoints = pointService.calculateTotalPoints(chargeHistories); // 수정: 총 포인트 계산
        List<PointUseHistoryDTO> pointUseHistories = pointService.findAllUsePointByUserId(userId); //포인트 사용내역

        System.out.println(chargeHistories.size());
        model.addAttribute("chargeHistories", chargeHistories);
        model.addAttribute("totalPoints", totalPoints);
        model.addAttribute("pointUseHistories", pointUseHistories); //포인트 사용내역

        return "/ljg/pointPage"; // pointPage는 Thymeleaf 템플릿의 이름입니다.
    }

    @GetMapping("/test")
    public String pointCharge() {
        return "/ljg/pointCharge";
    }

    @GetMapping("/chargePoint")
    @ResponseBody
    public String chargePoint(@RequestParam("userId") long userId, @RequestParam("point") int point) {
        PointDTO pointDTO = new PointDTO(); // Create a new PointDTO object
        pointDTO.setUserId(userId); // Set the user ID
        pointDTO.setPoint(point); // Set the charged point
        pointService.pointCharge(pointDTO); // Call the pointCharge method to save the point
        return "success"; // Return a success message
    }

    //포인트 사용 내역
    @PostMapping("/usePoint")
    @ResponseBody
    public String usePoint(@RequestParam("userId") long userId, @RequestParam("price") int price) {
        PointUseHistoryDTO pointUseHistoryDTO = new PointUseHistoryDTO();
        pointUseHistoryDTO.setUserId(userId);
        pointUseHistoryDTO.setPrice(price);
        pointService.usePoints(pointUseHistoryDTO);
        return "success";
    }
}
