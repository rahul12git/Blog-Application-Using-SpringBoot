package com.my.blog.payloads;

import com.my.blog.entities.Category;
import com.my.blog.entities.Comment;
import com.my.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class PostDto {
private Integer id;
    private String title;
    private String content;

    private String imageName;
    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

private Set<CommentDto> comments= new HashSet<>();

}
