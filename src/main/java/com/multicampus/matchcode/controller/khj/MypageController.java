package com.multicampus.matchcode.controller.khj;

import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.MemberAndPointDTO;
import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.service.khj.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {

    @Autowired
    MypageService service;

    //마이페이지 기본 화면
    @GetMapping("mypage")
    public String mypage(Model model){
        MemberAndPointDTO memberAndPoint = service.getMemberAndPoint(1); // Replace with the actual member ID
        model.addAttribute("memberAndPoint", memberAndPoint);
        return "khj/mypage";
    }

    //매치히스토리
    @GetMapping("matchhistory")
    public  String matchhistory(){

        return "khj/matchhistory";
    }

    //포지션 설정
    @GetMapping("myposition")
    public  String myposition(){

        return "khj/myposition";
    }

    //개인정보
    @GetMapping("personal")
    public  String personal(){

        return "khj/personal";
    }

    //내 게시물
    @GetMapping("mypost")
    public  String mypost(){

        return "khj/mypost";
    }

    //개인 기록
    @GetMapping("record")
    public  String record(){

        return "khj/record";
    }

}
