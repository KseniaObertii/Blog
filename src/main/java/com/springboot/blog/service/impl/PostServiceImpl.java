package com.springboot.blog.service.impl;

import com.springboot.blog.dao.PostDao;
import com.springboot.blog.model.Post;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostDao postDao;

    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public Post create(Post post) {
        return postDao.save(post);
    }

    @Override
    public List<Post> getAll() {
        return postDao.findAll();
    }

    @Override
    public Post getById(Long id) {
        return postDao.getReferenceById(id);
    }

    @Override
    public Post update(Post post) {
        return postDao.save(post);
    }

    @Override
    public void delete(Long id) {
        postDao.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return postDao.existsById(id);
    }
}
