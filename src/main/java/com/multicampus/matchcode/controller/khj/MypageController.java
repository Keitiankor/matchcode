package com.multicampus.matchcode.controller.khj;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.model.request.khj.MatchResultRequest;
import com.multicampus.matchcode.model.request.khj.MemberInfoRequest;
import com.multicampus.matchcode.model.request.khj.RatingRequest;
import com.multicampus.matchcode.service.khj.MyHistoryService;
import com.multicampus.matchcode.service.khj.MyPostService;
import com.multicampus.matchcode.service.khj.MypageService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MypageController {

    @Autowired
    MypageService service;

    @Autowired
    MyHistoryService myHistoryService;

    @Autowired
    MyPostService MyPost;

    @ModelAttribute("memberId")
    public Long getMemberId(@SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO loggedInMember) {
        if (loggedInMember != null) {
            return loggedInMember.getId();
        }
        return null; // 세션에 memberId가 없는 경우 null 반환
    }

    //마이페이지 기본 화면
    @GetMapping("mypage")
    public String mypage(Model model, @ModelAttribute("memberId") Long memberId) {
        if (memberId == null) {
            // 로그인하지 않은 경우, 알림 메시지 화면으로 이동
            return "khj/message";
        }

        MemberInfoRequest memberInfo = service.getMemberInfo(memberId);
        model.addAttribute("memberInfo", memberInfo);
        return "khj/mypage";
    }

    //매치히스토리

    @GetMapping("matchhistory")
    public String matchhistory() {
        return "khj/matchhistory";
    }

    //매치 히스토리 내에서 종목별 페이지
    @GetMapping("/loadsportsdata")
    public String loadSportsData(long sportsId, Model model, @ModelAttribute("memberId") long memberId) {
        RatingRequest ratingRequest = myHistoryService.getRatingBySportsIdAndMemberId(sportsId, memberId);
        List<MatchResultRequest> matchResults = myHistoryService.getMatchResultsBySportsIdAndMemberId(sportsId, memberId);

        model.addAttribute("rating", ratingRequest);
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
    public String personal(Model model, @ModelAttribute("memberId") long memberId) {
        MemberDTO member = service.getMemberById(memberId);

        model.addAttribute("member", member);
        return "khj/personal";
    }

    //내 게시물

    @GetMapping("mypost")
    public String mypost(Model model, @ModelAttribute("memberId") long memberId) {
        List<PostDTO> myPosts = MyPost.getMyPostsByMemberId(memberId);
        List<ReplyDTO> myReplies = MyPost.getMyRepliesByMemberId(memberId);

        // 게시글을 최신 날짜 기준으로 정렬,상위 5개 게시글만 추출
        myPosts.sort(Comparator.comparing(PostDTO::getCreatedDate).reversed());
        List<PostDTO> fivePosts = myPosts.stream().limit(5).collect(Collectors.toList());

        // 댓글도 최신 날짜 기준으로 정렬 후 상위 5개 게시글만 추출
        myReplies.sort(Comparator.comparing(ReplyDTO::getCreatedDate).reversed());
        List<ReplyDTO> fiveReplies = myReplies.stream().limit(5).collect(Collectors.toList());

        model.addAttribute("myPosts", fivePosts);
        model.addAttribute("myReplies", fiveReplies);

        return "khj/mypost";
    }

    @GetMapping("personalupdate")
    public String personalupdate(Model model, @ModelAttribute("memberId") long memberId) {
        MemberDTO member = service.getMemberById(memberId);

        model.addAttribute("member", member);
        return "khj/personalupdate";
    }
}
