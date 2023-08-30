package com.multicampus.matchcode.model.request.hgdd;



import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Builder
@Data
public class PostInsertRequest {

    private List<Long> sportsId;
    /*private long userId;*/
    private String title;
    private String content;
    @CreationTimestamp
    private Timestamp createdDate;
    private boolean privates;

}
