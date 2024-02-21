package com.my.blog.services;

import com.my.blog.entities.Post;
import com.my.blog.payloads.PostDto;
import com.my.blog.payloads.PostResponse;
import io.swagger.models.auth.In;

import java.util.List;

public interface PostService {
    //Create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

   //Update
    PostDto updatePost(PostDto postDto, Integer postId);

    //Delete
    void deletePost(Integer postId);

  //get all post
   PostResponse getAllPost(Integer pageNumber, Integer pageSize);
  //get  single posts by id
   PostDto getPostById(Integer postId);

   //  get All post By Category
   List<PostDto> getPostByCategory(Integer categoryId);

   //get all post by user
  List<PostDto> getPostsByUser(Integer postId);

    //Search Post
    List<Post> searchPosts(String keyword);

}
