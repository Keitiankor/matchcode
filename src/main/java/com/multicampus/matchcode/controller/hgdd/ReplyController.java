package com.multicampus.matchcode.controller.hgdd;

import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.service.hgdd.PostService;
import com.multicampus.matchcode.service.hgdd.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/reply/insert")
    public @ResponseBody List<ReplyDTO> insert(@ModelAttribute  ReplyDTO replyDTO){

        System.out.println("replyDTO = " + replyDTO);
        replyService.insert(replyDTO);

        List<ReplyDTO> replyDTOList = replyService.list(replyDTO.getPostId());
        return replyDTOList ;
    }
}
