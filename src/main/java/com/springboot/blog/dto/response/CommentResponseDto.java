package com.springboot.blog.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponseDto {
    private Long id;
    private String body;
    private String email;
}
