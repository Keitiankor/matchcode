package com.multicampus.matchcode.controller.keitian;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.request.keitian.LoginRequest;
import com.multicampus.matchcode.model.request.keitian.RegisterRequest;
import com.multicampus.matchcode.service.keitian.MemberService;
import com.multicampus.matchcode.util.component.MailComponent;
import com.multicampus.matchcode.util.constants.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MemberController {

    @Autowired
    MemberService service;

    @Autowired
    MailComponent mailSender;

    @GetMapping("login")
    public String gMemberLogin(@SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO dto) {
        if (dto == null) {
            return "keitian/login";
        } else {
            return "index";
        }
    }

    @GetMapping("register")
    public String gMemberRegister(@SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO dto) {
        if (dto == null) {
            return "keitian/register";
        } else {
            return "index";
        }
    }

    @PostMapping("login")
    public String pMemberLogin(HttpServletRequest hRequest, LoginRequest request) {
        HttpSession session = hRequest.getSession(true);
        MemberDTO dto = service.getId(request.getAccount(), request.getPassword());
        if (dto != null) {
            session.setAttribute(SessionConstant.MEMBER_DTO, dto);
        } else {
            return "keitian/login";
        }
        return "redirect:";
    }

    @PostMapping("register")
    public String pMemberRegister(RegisterRequest request) {
        if (request.isVerifyied() && request.isAccountNotDup()){
            service.insert(request);
            return "redirect:";
        }
        return "keitian/register";
    }

    @PostMapping("register/accountduplicationcheck")
    @ResponseBody
    public boolean pAccountNotDupe(@RequestParam String account) {
        return service.isAccountDup(account);
    }

    @PostMapping("register/emailverifying")
    @ResponseBody
    public String pMemberRegisterEmailVerify(HttpServletRequest hRequest, @RequestParam String mailAddress) {
        String verifyString = mailSender.sendVerifyingMail(mailAddress);
        if (verifyString != null) {
            hRequest.getSession(false).setAttribute(SessionConstant.VERIFY_STRING, verifyString);
            return "메일이 정상 발송되었습니다.";
        }
        return "메일 발송중 오류가 발생했습니다.";
    }

    @PostMapping("register/verifyingcheck")
    @ResponseBody
    public Boolean pMemberVerifyingCheck(
        @SessionAttribute(name = SessionConstant.VERIFY_STRING, required = true) String verifyString,
        @RequestParam String inputString
    ) {
        return verifyString.equals(inputString);
    }

    @GetMapping("login/findpw")
    public String gFindpw(){
        return "keitian/findpw";
    }

    @PostMapping("login/findpw")
    @ResponseBody
    public String pFindpw(String email){
        return "";
    }
}
