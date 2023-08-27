package com.multicampus.matchcode.controller.jun;

import com.multicampus.matchcode.model.request.jun.SportCenterRequest;
import com.multicampus.matchcode.service.jun.SportCenterSercvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sportCenter")
public class SportCenterController {

    @Autowired
    private SportCenterSercvice sportCenterSercvice;

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

    //체육 시설 등록
    @GetMapping("createSportCenter")
    public String createSportCenter() {
        return "sportCenter/createSportCenter";
    }

    @PostMapping("createSportCenter2")
    public String createSportCenter2(SportCenterRequest sportCenterRequest) {
        System.out.println("managerName" + sportCenterRequest.getManagerName());
        System.out.println("phone" + sportCenterRequest.getPhone());
        System.out.println("SportCenterName" + sportCenterRequest.getSportCenterName());
        System.out.println("areaId" + sportCenterRequest.getAreaId());
        System.out.println("resistrationCode" + sportCenterRequest.getResistrationCode());

        sportCenterSercvice.save(sportCenterRequest);

        return "redirect:/review/viewSportCenter";
    }

    //체육 시설 수정
    @GetMapping("updateSportCenter")
    public String updateSportCenter() {
        return "sportCenter/updateSportCenter";
    }

    //관리자 개인 정보 등록
    @GetMapping("createAdmin")
    public String createAdmin() {
        return "sportCenter/createAdmin";
    }

    //사업자명 등록
    @GetMapping("createBusiness")
    public String createBusiness() {
        return "sportCenter/createBusiness";
    }

    //사업자명과 관리자 이름 대조
    @GetMapping("compare")
    public String compare() {
        return "sportCenter/compare";
    }
}
