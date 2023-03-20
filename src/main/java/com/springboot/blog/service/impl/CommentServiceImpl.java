package com.springboot.blog.service.impl;

import com.springboot.blog.dao.CommentDao;
import com.springboot.blog.model.Comment;
import com.springboot.blog.model.Post;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final PostService postService;

    public CommentServiceImpl(CommentDao commentDao, PostService postService) {
        this.commentDao = commentDao;
        this.postService = postService;
    }

    @Override
    public Comment create(Comment comment, Long postId) {
        Post post = postService.getById(postId);
        post.getComments().add(comment);
        return commentDao.save(comment);
    }

    @Override
    public List<Comment> getAllByPostId(Long postId) {
        return postService.getById(postId).getComments();
    }

    @Override
    public Comment getById(Long commentId) {
        return commentDao.getReferenceById(commentId);
    }

    @Override
    public Comment update(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public void delete(Long commentId) {
        commentDao.deleteById(commentId);
    }

    @Override
    public boolean exists(Long commentId) {
        return commentDao.existsById(commentId);
    }

    public boolean exists(Long commentId, Long i) {
        return commentDao.existsById(commentId);
    }
}
