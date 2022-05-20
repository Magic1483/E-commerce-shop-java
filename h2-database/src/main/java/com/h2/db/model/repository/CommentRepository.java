package com.h2.db.model.repository;

import com.h2.db.model.CommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity,Long> {
}
