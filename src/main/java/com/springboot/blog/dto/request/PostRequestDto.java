package com.springboot.blog.dto.request;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String description;
    private String content;
}
