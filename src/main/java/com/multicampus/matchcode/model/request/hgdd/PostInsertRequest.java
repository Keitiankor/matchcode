package com.multicampus.matchcode.model.request.hgdd;


import com.multicampus.matchcode.model.entity.MemberDTO;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Builder
@Data
public class PostInsertRequest {


    /*private long userId;*/
    private String title;
    private String content;
    @CreationTimestamp
    private Timestamp createdDate;
    private boolean privates;
}
