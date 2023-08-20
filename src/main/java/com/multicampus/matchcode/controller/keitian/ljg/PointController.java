package com.multicampus.matchcode.controller.keitian.ljg;



import com.multicampus.matchcode.model.entity.ChargeHistoryDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.repository.ChargeHistoryRepository;
import com.multicampus.matchcode.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//포인트 조회
@Controller
public class PointController {


    private final PointRepository pointRepository;

    private final ChargeHistoryRepository chargeHistoryRepository;

    @Autowired
    public PointController(PointRepository pointRepository, ChargeHistoryRepository chargeHistoryRepository) {
        this.pointRepository = pointRepository;
        this.chargeHistoryRepository = chargeHistoryRepository;
    }


    //포인트표시
    @GetMapping("/point")
    public String viewPointPage(Model model, long userId) {
        PointDTO result = pointRepository.findByuserId(userId);
        model.addAttribute("Point", result.getPoint());
        //충전내역
        List<ChargeHistoryDTO> chargeHistories = chargeHistoryRepository.findAll();
        model.addAttribute("chargeHistories", chargeHistories);

        return "pointPage"; // pointPage는 Thymeleaf 템플릿의 이름입니다.
    }
}



