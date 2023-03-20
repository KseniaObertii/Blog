package com.springboot.blog.controller;

import com.springboot.blog.dto.request.PostRequestDto;
import com.springboot.blog.dto.response.CommentResponseDto;
import com.springboot.blog.dto.response.PostResponseDto;
import com.springboot.blog.model.Post;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.PostService;
import com.springboot.blog.service.mapper.CommentMapper;
import com.springboot.blog.service.mapper.PostMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(CommentService commentService, CommentMapper commentMapper, PostService postService, PostMapper postMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @PostMapping
    public PostResponseDto create(@RequestBody PostRequestDto postRequestDto) {
        Post post = postService.create(postMapper.mapToModel(postRequestDto));
        return postMapper.mapToDto(post);
    }

    @GetMapping
    public List<PostResponseDto> getAll() {
        return postService.getAll().stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PostResponseDto getById(@PathVariable Long id) {
        if (postService.exists(id)) {
            return postMapper.mapToDto(postService.getById(id));
        }
        throw new EntityNotFoundException("Post with id " + id + " not found");
    }

    @GetMapping("/{postId}/comments")
    public List<CommentResponseDto> getAllCommentsByPostId(@PathVariable Long postId) {
        return commentService.getAllByPostId(postId).stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public PostResponseDto update(@RequestBody PostRequestDto requestDto, @PathVariable Long id) {
        Post post = postMapper.mapToModel(requestDto);
        post.setId(id);
        return postMapper.mapToDto(postService.update(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }
}
