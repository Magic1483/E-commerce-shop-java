package com.h2.db.service;

import com.h2.db.model.CommentEntity;
import com.h2.db.model.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommentService {

    @Autowired
    CommentRepository repository;

    public CommentEntity createComment(CommentEntity entity)
    {
        entity=repository.save(entity);
        return entity;
    }
}
