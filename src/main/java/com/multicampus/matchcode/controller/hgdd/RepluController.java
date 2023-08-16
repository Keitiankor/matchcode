package com.multicampus.matchcode.controller.hgdd;

import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.service.hgdd.PostService;
import com.multicampus.matchcode.service.hgdd.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RepluController {

    @Autowired
   private  ReplyService replyService;
    @Autowired
   private PostService postService;



    @PostMapping("/post/comment/insert")
    private String insertComment(@RequestParam("idx") long idx,@RequestParam("content")String content) throws Exception{
        ReplyDTO replyDTO=new ReplyDTO();
        replyDTO.setComment(content);
        replyDTO.setId(idx);
        replyService.insert(replyDTO);


        return redirect_url;
    }

}
