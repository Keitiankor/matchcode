package com.multicampus.matchcode.controller.hyuk;

import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.request.hyuk.Match;
import com.multicampus.matchcode.service.hyuk.MatchMemberService;
import com.multicampus.matchcode.service.hyuk.MatchService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchController {

    @Autowired
    MatchService matchService;

    @Autowired
    MatchMemberService matchMemberService;

    @GetMapping("/list")
    public String listByRegionAndSports(Model model,
                                        @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "region", defaultValue = "0") long regionId,
                                        @RequestParam(value = "sports", defaultValue = "0") long sportsId) {
        List<MatchDTO> matchList;
        Integer[] pageList;

        if (regionId != 0 && sportsId != 0) {
            // regionId와 sportsId가 0이 아닌 경우, 해당 지역과 종목 데이터만 조회
            matchList = matchService.getMatchlistByRegionAndSports(pageNum, regionId, sportsId);
            pageList = matchService.getPageListByRegionAndSports(pageNum, regionId, sportsId);
        } else {
            // regionId와 sportsId가 모두 0인 경우, 전체 데이터 조회
            matchList = matchService.getMatchlist(pageNum);
            pageList = matchService.getPageList(pageNum);
        }

        model.addAttribute("matchList", matchList);
        model.addAttribute("pageList", pageList);
        model.addAttribute("selectedRegion", regionId); // 선택된 지역을 뷰로 전달
        model.addAttribute("selectedSports", sportsId); // 선택된 종목을 뷰로 전달

        return "match/list";
    }



    /*    @GetMapping({"", "/list"})
    public String list(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Match> list = matchService.matchList(pageable);;

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "match/list";
    }*/

    // 글쓰는 페이지

    @GetMapping("/post")
    public String write() {
        return "match/write";
    }

/*    @GetMapping("/test")
    public String test() {
        return "match/test";
    }*/

    // 글을 쓴 뒤 POST 메서드로 글 쓴 내용을 DB에 저장
    // 그 후에는 /list 경로로 리디렉션해준다.

    @PostMapping("/post2")
    public String write(Match match, @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = true) MemberDTO memberDTO, Model model) {

        long matchId = matchService.savePost(match); // 매치를 저장하고 ID를 받아옴

        ///////////////////////
       // memberDTO = new MemberDTO();
      //  memberDTO.setId(1);
      //  session.setAttribute("memberDTO", memberDTO);
     //   memberDTO = (MemberDTO)session.getAttribute("memberDTO");
        //////////////////////

        long memberId = memberDTO.getId(); // 세션에서 로그인한 사용자의 ID를 가져옴

        matchMemberService.addMatchLeader(matchId, memberId);
        return "redirect:/match/list";
    }


    // 게시물 상세 페이지이며, {no}로 페이지 넘버를 받는다.
    // PathVariable 애노테이션을 통해 no를 받음

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        MatchDTO matchDTO = matchService.getPost(no);
/*        List<String> list = matchService.getMatchMemberList(no);*/
        model.addAttribute("matchDto", matchDTO);
/*        model.addAttribute("list", list);*/
        return "match/detail";
    }


    // 게시물 수정 페이지이며, {no}로 페이지 넘버를 받는다.

    /*    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        MatchDTO matchDTO = matchService.getPost(no);

        model.addAttribute("matchDto", matchDTO);
        return "match/update";
    }

    // 위는 GET 메서드이며, PUT 메서드를 이용해 게시물 수정한 부분에 대해 적용

    @PutMapping("/post/edit/{no}")
    public String update(@RequestParam long postId, MatchData data) {
        matchService.updatePost(postId, data);

        return "redirect:/match/list";
    }*/

    // 게시물 삭제는 deletePost 메서드를 사용하여 간단하게 삭제할 수 있다.

    @PostMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        matchService.deletePost(no);

        return "redirect:/match/list";
    }

    // 검색
    // keyword를 view로부터 전달 받고
    // Service로부터 받은 matchDtoList를 model의 attribute로 전달해준다.

    /*    @GetMapping("/match/search")
        public String search(@RequestParam(value = "keyword") String keyword, Model model) {
            List<MatchDTO> matchDtoList = matchService.searchPosts(keyword);

            model.addAttribute("matchList", matchDtoList);

            return "match/list";
        }

        @GetMapping("/getmatchesbyregion")
        }*/
    @GetMapping("/getmatchesbyregionandsports")
    @ResponseBody
    public List<MatchDTO> getMatchesByRegionAndSports(
            @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "region", defaultValue = "0") long regionId,
            @RequestParam(value = "sports", defaultValue = "0") long sportsId) {
        // 이 부분에서 필요한 데이터를 조회하여 List<MatchDTO> 형태로 반환
        List<MatchDTO> matchList = matchService.getMatchlistByRegionAndSports(pageNum, regionId, sportsId);
        return matchList;
    }

}
