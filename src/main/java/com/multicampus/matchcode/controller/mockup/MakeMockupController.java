package com.multicampus.matchcode.controller.mockup;

import com.multicampus.matchcode.service.mockup.MakeMockup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MakeMockupController {
    @Autowired
    MakeMockup mockup;
    @GetMapping("makemockupdata")
    public String makeMockup() {
        mockup.makeMockupMap(100);
        return "redirect:";
    }
}
