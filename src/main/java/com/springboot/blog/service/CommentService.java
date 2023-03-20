package com.springboot.blog.service;

import com.springboot.blog.model.Comment;

import java.util.List;

public interface CommentService {
    Comment create(Comment comment, Long postId);

    List<Comment> getAllByPostId(Long postId);

    Comment getById(Long commentId);

    Comment update(Comment comment);

    void delete(Long commentId);

    boolean exists(Long commentId);
}
