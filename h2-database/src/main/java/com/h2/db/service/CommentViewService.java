package com.h2.db.service;


import com.h2.db.exception.RecordNotFoundException;
import com.h2.db.model.CheckorderEntity;
import com.h2.db.model.CommentViewEntity;
import com.h2.db.model.repository.CommentViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentViewService {

    @Autowired
    CommentViewRepository repository;

    public List<CommentViewEntity> getAllComments()
    {
        System.out.println("get all orders");
        List<CommentViewEntity> result=(List<CommentViewEntity>) repository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<CommentViewEntity>();
        }
    }

    public List<CommentViewEntity> getCommentViewEntityByGame(String game) throws RecordNotFoundException
    {
        List<CommentViewEntity> ent=  repository.findCommentViewEntityByGame(game);

        if(ent.size()>0) {
            return ent;
        } else {
            return new ArrayList<>();
        }
    }
}
