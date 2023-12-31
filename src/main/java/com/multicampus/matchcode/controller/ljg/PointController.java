package com.multicampus.matchcode.controller.ljg;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.model.request.ljg.ReserveRequest;
import com.multicampus.matchcode.repository.MapRepository;
import com.multicampus.matchcode.service.ljg.PointService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
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
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO member,
            Long memberId,
            Model model) {

        if (member != null) {
            List<PointDTO> chargeHistories = pointService.findAllByMemberId(member.getId());
            chargeHistories.sort(Comparator.comparing(PointDTO::getDate)
                                           .reversed());
            int totalPoints = pointService.calculateTotalPoints(chargeHistories); // 수정: 총 포인트 계산
            List<PointDTO> pointUseHistories = pointService.findAllByMemberId(member.getId()); //포인트 사용내역
            model.addAttribute("chargeHistories", chargeHistories);
            model.addAttribute("totalPoints", totalPoints);
            model.addAttribute("pointUseHistories", pointUseHistories); //포인트 사용내역

            return "/ljg/pointPage";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/test")
    public String pointCharge(
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO member,
            Model model) {
        model.addAttribute("memberId", member.getId());
        return "/ljg/pointCharge";
    }

    @GetMapping("/chargePoint")
    @ResponseBody
    public String chargePoint(
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO member,
            @RequestParam("point") int point) {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        PointDTO pointDTO = PointDTO.builder()
                                    .date(date)
                                    .details("충전")
                                    .memberId(member.getId())
                                    .point(point)
                                    .build();
        pointService.pointCharge(pointDTO);
        return "success";
    }


    @GetMapping("/test3")
    public String pointCharge2(
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO member,
            Model model) {
        model.addAttribute("memberId", member.getId());
        return "/ljg/pointCharge2";
    }

    @GetMapping("/chargePoint2")
    @ResponseBody
    public String chargePoint2(
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO member,
            @RequestParam("point") int point) {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        PointDTO pointDTO = PointDTO.builder()
                                    .date(date)
                                    .details("충전")
                                    .memberId(member.getId())
                                    .point(point)
                                    .build();
        pointService.pointCharge(pointDTO);
        return "success";
    }

    //결제페이지
    @GetMapping("/payPage")
    public String payPage(
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO member,
            @RequestParam("price") int price,
            @RequestParam("id") long id,
            @RequestParam("sportCenterName") String sportCenterName
            ,Model model) {
        // 모든 MapDTO를 가져옵니다.
        List<PointDTO> chargeHistories = pointService.findAllByMemberId(member.getId());
        int totalPoints = pointService.calculateTotalPoints(chargeHistories);

        model.addAttribute("memberId", member.getId());
        model.addAttribute("totalPoints", totalPoints);
        model.addAttribute("id", id);
        model.addAttribute("price", price);
        model.addAttribute("sportCenterName", sportCenterName);
        return "/ljg/paypage";
    }

    //결제처리
    @GetMapping("/payPoint")
    public String payPoints(HttpServletRequest request,
                            ReserveRequest reserveRequest
    ) {
        pointService.payPoints(reserveRequest);
        request.getSession()
               .setAttribute(SessionConstant.MEMBER_DTO, reserveRequest.getMemberId());
        return "/ljg/paypage";
    }

    //환불페이지
    @GetMapping("/refund")
    public String refundPoint(
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO member, Model model) {
        List<PointDTO> chargeHistories = pointService.findAllByMemberId(member.getId());
        int totalPoints = pointService.calculateTotalPoints(chargeHistories);
        model.addAttribute("totalPoints", totalPoints);
        model.addAttribute("memberId", member.getId());
        return "/ljg/refundPoint";
    }

    @GetMapping("/refundPoint")
    @ResponseBody
    public String refundPoints(
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO member,
            @RequestParam("point") int refundAmount) {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        PointDTO pointDTO = PointDTO.builder()
                                    .date(date)
                                    .details("환불")
                                    .memberId(member.getId())
                                    .point(-refundAmount)
                                    .build();
        pointService.refundPoints(pointDTO);
        if (pointDTO != null) {
            return "success";
        } else {
            return "failure";
        }
    }

}
