package com.my.blog.controllers;

import com.my.blog.entities.Post;
import com.my.blog.payloads.ApiResponse;
import com.my.blog.payloads.PostDto;
import com.my.blog.payloads.PostResponse;
import com.my.blog.services.FileService;
import com.my.blog.services.PostService;
import io.swagger.models.auth.In;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;

@Value("${project.image}")
private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {

        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }


    //get By user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getpostsByUser(
            @PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }
//get by Category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getpostsByCategory(@PathVariable Integer categoryId) {

        List<PostDto> post = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value="pageNumber",defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
            @RequestParam(value = "sortBy" ,defaultValue="postId", required = false)String sortBy,
            @RequestParam(value = "sortDir" ,defaultValue="asc", required = false)String sortDir
    ) {
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }


    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Post is deleted  sucessfully !!", true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatePost=this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }
    //search
    @GetMapping("posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable ("keywords") String keywords){
        List<PostDto> result= this.postService. searchPosts(keywords);
    return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
}

//Post image upload
@PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image,
                                                         @PathVariable Integer postId) throws IOException {
    PostDto postDto=this.postService.getPostById(postId);
     String fileName=this.fileService.uploadImage(path, image);
postDto.setImageName(fileName);
PostDto updatePost= this.postService.updatePost(postDto, postId);
return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }


    //Method to serve files
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable ("imageName") String imageName,
            HttpServletResponse response
    ) throws  IOException{
        InputStream resource = this.fileService.getResource(path ,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}