package com.springboot.blog.service.mapper;

import com.springboot.blog.dto.request.CommentRequestDto;
import com.springboot.blog.dto.response.CommentResponseDto;
import com.springboot.blog.model.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper implements RequestDtoMapper<CommentRequestDto, Comment>
        , ResponseDtoMapper<CommentResponseDto, Comment> {
    private final ModelMapper modelMapper;

    public CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Comment mapToModel(CommentRequestDto dto) {
        Comment comment = modelMapper.map(dto, Comment.class);
        return comment;
    }

    @Override
    public CommentResponseDto mapToDto(Comment comment) {
        CommentResponseDto commentResponseDto =
                modelMapper.map(comment, CommentResponseDto.class);
        return commentResponseDto;
    }
}
