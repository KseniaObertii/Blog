package com.springboot.blog.controller;

import com.springboot.blog.dto.request.CommentRequestDto;
import com.springboot.blog.dto.response.CommentResponseDto;
import com.springboot.blog.model.Comment;
import com.springboot.blog.model.Post;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.PostService;
import com.springboot.blog.service.mapper.CommentMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private static final String commentNotFoundMessage = "Comment with id %s not found";
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final PostService postService;

    public CommentController(CommentService commentService, CommentMapper commentMapper, PostService postService) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.postService = postService;
    }

    @PostMapping
    public CommentResponseDto create(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long postId) {
        return commentMapper.mapToDto(
                commentService.create(commentMapper.mapToModel(commentRequestDto), postId));
    }

    @GetMapping("/{commentId}")
    public CommentResponseDto getById(@PathVariable Long commentId) {
        Comment comment = commentService.getById(commentId);
        if (comment == null) {
            throw new EntityNotFoundException(String.format(commentNotFoundMessage, commentId));
        }
        return commentMapper.mapToDto(comment);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto update(@RequestBody CommentRequestDto requestDto,
                                     @PathVariable Long commentId) {
        if (commentService.getById(commentId) == null) {
            throw new EntityNotFoundException(String.format(commentNotFoundMessage, commentId));
        }
        Comment comment = commentMapper.mapToModel(requestDto);
        comment.setId(commentId);
        return commentMapper.mapToDto(commentService.update(comment));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> delete(@PathVariable Long commentId, @PathVariable Long postId) {
        if (postService.exists(postId)) {
            commentService.delete(commentId);
        } else {
            throw new EntityNotFoundException(String.format(commentNotFoundMessage, commentId, postId));
        }
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }
}
