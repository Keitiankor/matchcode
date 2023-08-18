package com.multicampus.matchcode.controller.khj;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MypageController {

    //마이페이지 기본 화면
    @GetMapping("/mypage")
    public  void mypage(){

    }

    //매치히스토리
    @GetMapping("/matchhistory")
    public  void matchhistory(){

    }

    //포지션 설정
    @GetMapping("/myposition")
    public  void myposition(){

    }

    //개인정보
    @GetMapping("/personal")
    public  void personal(){

    }

    //내 게시물
    @GetMapping("/mypost")
    public  void mypost(){

    }

    //개인 기록
    @GetMapping("/record")
    public  void record(){

    }

}
