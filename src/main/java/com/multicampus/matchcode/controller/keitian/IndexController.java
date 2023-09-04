package com.multicampus.matchcode.controller.keitian;

import com.multicampus.matchcode.service.keitian.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    IndexService service;
    IndexController(IndexService indexService) {
        this.service = indexService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("posts", service.getIndexPost());
        model.addAttribute("matches", service.getIndexMatch());
        return "index";
    }
}
