package com.multicampus.matchcode.model.request.hgdd;



import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Builder
@Data
public class PostInsertRequest {

    private String title;
    private String content;
    private boolean privates;

}