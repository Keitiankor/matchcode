package com.multicampus.matchcode.controller.keitian;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.request.keitian.LoginRequest;
import com.multicampus.matchcode.model.request.keitian.RegistserRequest;
import com.multicampus.matchcode.service.keitian.MemberService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MemberController {

    @Autowired
    MemberService service;

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
        System.out.println(request.getAccount());
        System.out.println(request.getPassword());
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
        int result = service.insert(request);
        if (result == -1) {
            return "redirect:regist";
        }
        return "redirect:";
    }
}
