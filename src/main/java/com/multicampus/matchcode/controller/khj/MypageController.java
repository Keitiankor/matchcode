package com.multicampus.matchcode.controller.khj;

import com.multicampus.matchcode.model.entity.*;
import com.multicampus.matchcode.model.request.khj.MatchResult;
import com.multicampus.matchcode.service.khj.MyHistoryService;
import com.multicampus.matchcode.service.khj.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MypageController {

    @Autowired
    MypageService service;

    @Autowired
    MyHistoryService myHistoryService;

    //마이페이지 기본 화면
    @GetMapping("mypage")
    public String mypage(Model model){ //만약 로그인 세션에서 받아온다면 매개변수는
        // Model model, @SessionAttribute("loggedInMember") MemberDTO loggedInMember
        //로 작성이 될 것

        // long memberId = loggedInMember.getId(); // 로그인한 회원의 memberid
        // 위 코드는, 이제 로그인 시, 세션에서 memberid를 받아올 경우를 상정한 코드임! 그 전까진 예시로 1,2 같은 예시값을 집어넣어두자.
        MemberAndPointDTO memberAndPoint = service.getMemberAndPoint(2); // 여기 memberId값에 이제 세션에서 받을 member의 id값을 넣으면 되는 것. 지금은 예시로 걍 2
        model.addAttribute("memberAndPoint", memberAndPoint);
        return "khj/mypage";

    }


    //매치히스토리

    @GetMapping("matchhistory")
    public  String matchhistory(){

        return "khj/matchhistory";
    }
    
    //매치 히스토리 내에서 종목별 페이지
    @GetMapping("/loadSportsData")
    public String loadSportsData(long sportsId, Model model) {
        List<MatchResult> matchResults = myHistoryService.getMatchResultsBySportsId(sportsId);
        model.addAttribute("matchResults", matchResults);
        return "khj/history";
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
    //개인 기록

}
