package com.multicampus.matchcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class KeyController {

    @GetMapping("kakaomapkey")
    public String getKakaoMapAPIKey() {
        return "0a82c18db98c21bd3c0af543d02f5083"; //kakaoMapAPI key
    }
}
