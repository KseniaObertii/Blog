package com.springboot.blog.service.mapper;

import com.springboot.blog.dto.request.PostRequestDto;
import com.springboot.blog.dto.response.PostResponseDto;
import com.springboot.blog.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements ResponseDtoMapper<PostResponseDto, Post>
        , RequestDtoMapper<PostRequestDto, Post>{
    private final ModelMapper modelMapper;

    public PostMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostResponseDto mapToDto(Post post) {
        return modelMapper.map(post, PostResponseDto.class);
    }

    @Override
    public Post mapToModel(PostRequestDto dto) {
        return modelMapper.map(dto, Post.class);
    }
}
