package com.my.blog.services;

import com.my.blog.payloads.CommentDto;
import io.swagger.models.auth.In;

public interface CommentService

{
    CommentDto createComment(CommentDto commentDto , Integer postId);

    void deleteComment(Integer commentId);
}
