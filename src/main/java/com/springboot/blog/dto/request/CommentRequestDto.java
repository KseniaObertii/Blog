package com.springboot.blog.dto.request;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String email;
    private String body;
}
