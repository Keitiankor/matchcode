package com.multicampus.matchcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api")
public class KeyController {

    @GetMapping("kakaomapkey")
    public String getKakaoMapAPIKey() {
        return "c80a75ce9529c87ee1ee975834c095bf"; //kakaoMapAPI key
    }
}

