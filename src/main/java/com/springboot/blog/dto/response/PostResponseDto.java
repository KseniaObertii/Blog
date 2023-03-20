package com.springboot.blog.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private List<CommentResponseDto> comments;
}
