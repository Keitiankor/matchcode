package com.multicampus.matchcode.controller.keitian;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.request.keitian.LoginRequest;
import com.multicampus.matchcode.model.request.keitian.RegistserRequest;
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
    public String gMemberLogin(@SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) Long id) {
        if (id == null) {
            return "keitian/login";
        } else {
            return "keitian/shortinfo";
        }
    }

    @GetMapping("regist")
    public String gMemberRegist(@SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) Long id) {
        if (id == null) {
            return "keitian/regist";
        } else {
            return "index";
        }
    }

    @PostMapping("login")
    public String pMemberLogin(HttpServletRequest hRequest, LoginRequest request) {
        HttpSession session = hRequest.getSession(true);
        MemberDTO dto = service.getId(request.getAccount(), request.getPassword());
        if (dto != null) {
            session.setAttribute(SessionConstant.MEMBER_ID, dto);
            return "redirect:";
        } else {
            return "redirect:login";
        }
    }

    @PostMapping("regist")
    public String pMemberRegist(RegistserRequest request) {
        if (request.isAccountNotDup()) {
            if (request.isVerifyied()) {
                service.insert(request);
            }
        } else {
            return "regist";
        }
        return "redirect:";
    }

    @PostMapping("regist/accountduplicationcheck")
    @ResponseBody
    public boolean pAccountNotDupe(@RequestParam String account) {
        return service.isAccountDup(account);
    }

    @PostMapping("regist/emailverifying")
    @ResponseBody
    public String pMemberRegistEmailVerify(HttpServletRequest hRequest, @RequestParam String mailAddress) {
        String verifyString = mailSender.sendVerifyingMail(mailAddress);
        if (verifyString != null) {
            hRequest.getSession(false).setAttribute(SessionConstant.VERIFY_STRING, verifyString);
            return "메일이 정상 발송되었습니다.";
        }
        return "메일 발송중 오류가 발생했습니다.";
    }

    @PostMapping("regist/verifyingcheck")
    @ResponseBody
    public Boolean pMemverVerifyingCheck(
            @SessionAttribute(name = SessionConstant.VERIFY_STRING, required = true) String verifyString,
            @RequestParam String inputString) {
        if (verifyString.equals(inputString)) {
            return true;
        }
        return false;
    }

}
