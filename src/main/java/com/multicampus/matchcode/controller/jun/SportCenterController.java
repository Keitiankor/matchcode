package com.multicampus.matchcode.controller.jun;

import com.multicampus.matchcode.model.request.jun.SportCenterRequest;
import com.multicampus.matchcode.service.jun.SportCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sportCenter")
public class SportCenterController {

    @Autowired
    private SportCenterService sportCenterService;

    //모든 유저 검색한 장소 표시
    @GetMapping("findSportCenter")
    public String findSportCenter() {

        return "sportCenter/findSportCenter";
    }

    //모든 유저가 다른 지역 체육시설 검색
    @GetMapping("differentSportCenter")
    public String differentSportCenter() {
        return "sportCenter/differentSportCenter";
    }

    //체육 시설 등록 폼 이동
    @GetMapping("createSportCenter")
    public String createSportCenter() {
        return "sportCenter/createSportCenter";
    }

    //체육 시설 등록
    @PostMapping("createSportCenter")
    public String createSportCenter2(SportCenterRequest sportCenterRequest) {
        System.out.println("managerName" + sportCenterRequest.getManagerName());
        System.out.println("phone" + sportCenterRequest.getPhone());
        System.out.println("SportCenterName" + sportCenterRequest.getSportCenterName());
        System.out.println("areaId" + sportCenterRequest.getAreaId());
        System.out.println("resistrationCode" + sportCenterRequest.getResistrationCode());

        sportCenterService.save(sportCenterRequest);

        return "redirect:/";
    }

    //체육 시설 수정
    @GetMapping("updateSportCenter")
    public String updateSportCenter(SportCenterRequest sportCenterRequest) {

        System.out.println("managerName" + sportCenterRequest.getManagerName());
        System.out.println("phone" + sportCenterRequest.getPhone());
        System.out.println("SportCenterName" + sportCenterRequest.getSportCenterName());
        System.out.println("areaId" + sportCenterRequest.getAreaId());
        System.out.println("resistrationCode" + sportCenterRequest.getResistrationCode());

        sportCenterService.save(sportCenterRequest);
        return "sportCenter/updateSportCenter";
    }

    //사업자명과 관리자 이름 대조
    @GetMapping("compare")
    public String compare() {
        return "sportCenter/compare";
    }
}
