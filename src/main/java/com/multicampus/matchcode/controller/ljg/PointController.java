package com.multicampus.matchcode.controller.ljg;


import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.model.request.ljg.ReserveRequest;
import com.multicampus.matchcode.service.ljg.PointService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;


//포인트 조회
@Controller
public class PointController {

    @Autowired
    private PointService pointService;

    //포인트표시
    @GetMapping("/point")
    public String viewPointPage(
        @SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) MemberDTO member,Long memberId,
        Model model) {
        if (member != null) {
            List<PointDTO> chargeHistories = pointService.findAllByUserId(member.getId());//(1/*member.getId()*/);//userId추가
            chargeHistories.sort(Comparator.comparing(PointDTO::getDate).reversed());
            int totalPoints = pointService.calculateTotalPoints(chargeHistories); // 수정: 총 포인트 계산
            List<PointDTO> pointUseHistories = pointService.findAllByUserId(member.getId()); //포인트 사용내역

            System.out.println(chargeHistories.size());
            model.addAttribute("chargeHistories", chargeHistories);
            model.addAttribute("totalPoints", totalPoints);
            model.addAttribute("pointUseHistories", pointUseHistories); //포인트 사용내역

            return "/ljg/pointPage"; // pointPage는 Thymeleaf 템플릿의 이름입니다.
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/test")
    public String pointCharge() {
        return "/ljg/pointCharge";
    }

    @GetMapping("/chargePoint")
    @ResponseBody
    public String chargePoint(@SessionAttribute(name = SessionConstant.MEMBER_ID, required = false)MemberDTO member,
                              @RequestParam("point")  int point) {

        //Long userId = member.getId();
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        PointDTO pointDTO = PointDTO
                .builder()
                .date(date)
                .memberId(member.getId())
                .point(point)
                .build();
        System.out.println(pointDTO);
        pointService.pointCharge(pointDTO); // Call the pointCharge method to save the point
        return "success"; // Return a success message
    }

    /////////////////
    @GetMapping("/test3")
    public String pointCharge2() {
        return "/ljg/pointCharge2";
    }

    @GetMapping("/chargePoint2")
    @ResponseBody
    public String chargePoint2(@SessionAttribute(name = SessionConstant.MEMBER_ID, required = false)MemberDTO member,
                               @RequestParam("point") int point) {
        //Long userId = member.getId();
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        PointDTO pointDTO = PointDTO
                .builder()
                .date(date)
                .memberId(member.getId())
                .point(point)
                .build();
        pointService.pointCharge(pointDTO); // Call the pointCharge method to save the point
        return "success"; // Return a success message
    }
    /////////////////////////
    //포인트 사용 내역
//결제페이지//////////////////////////////////
    @GetMapping("/payPage")
    public String payPage(@SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) MemberDTO member,
                          Model model) {
        //long userId = 1;
       // Long userId = member.getId();
        List<PointDTO> chargeHistories = pointService.findAllByUserId(member.getId());
        int totalPoints = pointService.calculateTotalPoints(chargeHistories);
        model.addAttribute("totalPoints", totalPoints);
        return "/ljg/payPage"; // PayPage 템플릿의 이름
    }
////////////////환불페이지//////////////
@GetMapping("/refund")
    public String refundPoint(){
        return"/ljg/refundPoint";
}
    @GetMapping("/refundPoint")
    @ResponseBody
    public String refundPoints(@SessionAttribute(name = SessionConstant.MEMBER_ID, required = false)MemberDTO member,
                               @RequestParam("refundAmount") int refundAmount) {

        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        PointDTO pointDTO = PointDTO
                .builder()
                .date(date)
                .memberId(member.getId())// Set refundAmount here
                .point(-refundAmount)
                .build();
        pointService.refundPoints(pointDTO);
        if (pointDTO != null) {
            return "success";
        } else {
            return "failure";
        }
    }
    ////////결제처리///////
    @GetMapping ("/payPoint")
    public String payPoints(HttpServletRequest request, ReserveRequest reserveRequest) {
        pointService.payPoints(reserveRequest);
        request.getSession().setAttribute(SessionConstant.MEMBER_ID, reserveRequest.getMemberId());
        return "";
    }
}
    //포인트환불
//    @GetMapping("/refundPoint")
//    @ResponseBody
//    public String refundPoint(@RequestParam("userId") long userId,
//                              @RequestParam("point") int point) {
//        PointDTO pointDTO = new PointDTO();
//        pointDTO.setUserId(userId);
//        pointDTO.setPoint(-point); // 음수 포인트 값은 환불을 나타냅니다
//        pointService.pointRefund(pointDTO); // 환불 처리를 위한 pointRefund 메서드 호출
//        return "success";
//    }
//    @GetMapping("/test2")
//    public String pointRefund() {
//        return "pointRefund";
//
//    }




