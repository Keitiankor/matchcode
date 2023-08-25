package com.multicampus.matchcode.controller.jun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sportCenter")
public class SportCenterController {

    //모든 유저 검색한 장소 표시
    @GetMapping("sportCenter/findSportCenter")
    public String findSportCenter() {

        return "sportCenter/findSportCenter";
    }
    //모든 유저가 다른 지역 체육시설 검색
    @GetMapping("sportCenter/differentSportCenter")
    public String differentSportCenter() {

        return "sportCenter/differentSportCenter";
    }

    //체육 시설 등록
    @GetMapping("sportCenter/createSportCenter")
    public String createSportCenter() {

        return "sportCenter/createSportCenter";
    }

    //체육 시설 수정
    @GetMapping("sportCenter/updateSportCenter")
    public String updateSportCenter() {

        return "sportCenter/updateSportCenter";
    }

    //관리자 개인 정보 등록
    @GetMapping("sportCenter/createAdmin")
    public String createAdmin() {

        return "sportCenter/createAdmin";
    }

    //사업자명 등록
    @GetMapping("sportCenter/createBusiness")
    public String createBusiness() {

        return "sportCenter/createBusiness";
    }

    //사업자명과 관리자 이름 대조
    @GetMapping("sportCenter/compare")
    public String compare() {

        return "sportCenter/compare";
    }

}
