package com.springboot.blog.service.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
