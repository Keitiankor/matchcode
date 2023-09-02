package com.multicampus.matchcode.controller.jun;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.request.jun.SportCenterRequest;
import com.multicampus.matchcode.service.jun.SportCenterService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("sportCenter")
public class SportCenterController {

    @Autowired
    private SportCenterService sportCenterService;

    //모든 유저 검색한 장소 표시
    @GetMapping("findSportCenter")
    public String findSportCenter(Model model) {

        return "sportCenter/findSportCenter";
    }

    //시설 리스트 페이지
    @GetMapping("listSportCenter")
    public String detailSportCenter(Model model, MapDTO mapDTO) {
        List<MapDTO> mapList = sportCenterService.select(mapDTO);
        model.addAttribute("sportCenterList", mapList);
        return "sportCenter/listSportCenter";
    }

    //모든 유저가 다른 지역 체육시설 검색
    @GetMapping("differentSportCenter")
    public String differentSportCenter() {
        return "sportCenter/differentSportCenter";
    }

    //체육 시설 등록 폼 이동
    @GetMapping("createSportCenter")
    public String createSportCenter(@SessionAttribute(name = SessionConstant.MEMBER_DTO) MemberDTO memberDTO) {
        if (memberDTO == null) {
            return "redirect:/login";
        }
        return "sportCenter/createSportCenter";
    }

    //체육 시설 등록
    @PostMapping("createSportCenter")
    public String createSportCenter2(SportCenterRequest sportCenterRequest) {
        System.out.println("managerName : " + sportCenterRequest.getManagerName());
        System.out.println("phone : " + sportCenterRequest.getPhone());
        System.out.println("SportCenterName : " + sportCenterRequest.getSportCenterName());
        System.out.println("areaId : " + sportCenterRequest.getAreaId());
        System.out.println("resistrationCode : " + sportCenterRequest.getResistrationCode());
        System.out.println("latitude : " + sportCenterRequest.getLatitude());
        System.out.println("longitude : " + sportCenterRequest.getLongitude());

        sportCenterService.save(sportCenterRequest);

        return "redirect:/";
    }

    //체육 시설 수정 폼 이동
    @GetMapping("updateSportCenter")
    public String updateSportCenter(@RequestParam long id, SportCenterRequest sportCenterRequest, Model model) {

        System.out.println("managerName" + sportCenterRequest.getManagerName());
        System.out.println("phone" + sportCenterRequest.getPhone());
        System.out.println("SportCenterName" + sportCenterRequest.getSportCenterName());
        System.out.println("resistrationCode" + sportCenterRequest.getResistrationCode());
        MapDTO mapDTO = sportCenterService.findById(id);
        model.addAttribute("mapDTO", mapDTO);

        //sportCenterService.save(sportCenterRequest);
        return "sportCenter/updateSportCenter";
    }

    //체육 시설 수정
    @PostMapping("updateSportCenter")
    public String updateSportCenter2(SportCenterRequest sportCenterRequest) {

        sportCenterService.save(sportCenterRequest);

        return ":/";
    }

    //사업자명과 관리자 이름 대조
    @GetMapping("compare")
    public String compare() {
        return "sportCenter/compare";
    }
}
