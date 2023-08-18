package com.multicampus.matchcode.controller.keitian;

import com.multicampus.matchcode.model.request.keitian.LoginRequest;
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
    public String gMemberLogin(@SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) long id) {
        if (id == -1) {
            return "keitian/login";
        } else {
            return "keitian/shortinfo";
        }
    }

    @PostMapping("login")
    public String pMemberLogin(HttpServletRequest hRequest, LoginRequest request) {
        HttpSession session = hRequest.getSession(true);
        session.setAttribute(SessionConstant.MEMBER_ID, service.getId(request.getAccount(), request.getPassword()));
        return "redirect:index";
    }
}
