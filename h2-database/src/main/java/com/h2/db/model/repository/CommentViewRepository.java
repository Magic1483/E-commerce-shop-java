package com.h2.db.model.repository;

import com.h2.db.model.CheckorderEntity;
import com.h2.db.model.CommentViewEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentViewRepository extends CrudRepository<CommentViewEntity,Long> {

    @Query("select o from CommentViewEntity o where o.game=:game")
    List<CommentViewEntity> findCommentViewEntityByGame(@Param("game") String game);
}
