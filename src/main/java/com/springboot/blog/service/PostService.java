package com.springboot.blog.service;

import com.springboot.blog.model.Post;

import java.util.List;

public interface PostService {
    Post create(Post post);

    List<Post> getAll();

    Post getById(Long id);

    Post update(Post post);

    void delete(Long id);

    boolean exists(Long id);
}
