package com.my.blog.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDto> content;
    private int pageNumber ;
    private int pageSize ;
    private long totalElements;
    private int totalPage;
    private  boolean lastPages;


}
