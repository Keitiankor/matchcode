package com.multicampus.matchcode.controller.khj;

import com.multicampus.matchcode.model.entity.EmblemDTO;
import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.RatingDTO;
import com.multicampus.matchcode.model.request.khj.MatchResult;
import com.multicampus.matchcode.model.request.khj.MemberAndPointRequest;
import com.multicampus.matchcode.repository.RatingRepository;
import com.multicampus.matchcode.service.khj.MyHistoryService;
import com.multicampus.matchcode.service.khj.MypageService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MypageController {

    @Autowired
    MypageService service;

    @Autowired
    MyHistoryService myHistoryService;

    //마이페이지 기본 화면
    @GetMapping("mypage")
    public String mypage(Model model) { //만약 로그인 세션에서 받아온다면 매개변수는
        // Model model, @SessionAttribute("loggedInMember") MemberDTO loggedInMember
        //로 작성이 될 것

        // long memberId = loggedInMember.getId(); // 로그인한 회원의 memberid
        // 위 코드는, 이제 로그인 시, 세션에서 memberid를 받아올 경우를 상정한 코드임! 그 전까진 예시로 memberId값에 1,2 같은 예시값을 집어넣어두자.
        MemberAndPointRequest nameAndPoint = service.getMemberAndPoint(2);
        model.addAttribute("nameAndPoint", nameAndPoint);
        return "khj/mypage";
    }

    //매치히스토리

    @GetMapping("matchhistory")
    public String matchhistory() {
        return "khj/matchhistory";
    }

    //매치 히스토리 내에서 종목별 페이지
    @GetMapping("/loadsportsdata")
    public String loadSportsData(long sportsId, Model model) {
        RatingDTO rating = myHistoryService.getRatingBySportsIdAndUserId(sportsId, 2);
        List<MatchResult> matchResults = myHistoryService.getMatchResultsBySportsId(sportsId);
        EmblemDTO emblem = myHistoryService.getEmblemById(rating.getEmblemId());

        model.addAttribute("emblem", emblem);
        model.addAttribute("rating", rating);
        model.addAttribute("matchResults", matchResults);
        return "khj/history";
    }

    //매치 기록으로 매치 멤버들 찾기
    @GetMapping("/loadmatchdata")
    @ResponseBody
    public List<String> loadMatchData(@RequestParam String matchId) {
        System.out.println(matchId);
        return myHistoryService.getMembersByMatchId(Long.parseLong(matchId));
    }

    //매너온도 증감은 지금은 잘 몰라서 보류.

//    //매치 멤버 id로 매치 멤버의 매너온도 조작
//    //매너온도 증가시
//    @PostMapping
//    public ResponseEntity<String> increaseManner(@RequestParam long memberId){
//        boolean success = myHistoryService.increaseManner(memberId);
//        if (success) {
//            return ResponseEntity.ok("매너 온도 증가!");
//        } else {
//            return ResponseEntity.badRequest().body("Failed to increase manner");
//        }
//    }
//
//    //매너온도 감소시
//    @PostMapping("/decreaseManner")
//    public ResponseEntity<String> decreaseManner(@RequestParam long memberId) {
//        boolean success = myHistoryService.decreaseManner(memberId);
//        if (success) {
//            return ResponseEntity.ok("매너 온도 감소!");
//        } else {
//            return ResponseEntity.badRequest().body("Failed to decrease manner");
//        }
//    }

    //개인정보

    @GetMapping("personal")
    public String personal(Model model) {
        MemberDTO member = service.getMemberById(2);

        model.addAttribute("member", member);
        return "khj/personal";
    }

    //내 게시물

    @GetMapping("mypost")
    public String mypost() {
        return "khj/mypost";
    }

}
