package com.my.blog.repository;

import com.my.blog.entities.Category;
import com.my.blog.entities.Post;
import com.my.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo  extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

}
