package com.multicampus.matchcode.controller.hyuk;

import com.multicampus.matchcode.service.hyuk.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("match")
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping("matchSystem")
    public String matchSystem(Model model) {

        return "match/matchSystem";
    }










}
